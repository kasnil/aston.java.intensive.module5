package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.ArrayUtils;
import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.NotSupportedException;
import aston.java.intensive.module5.utils.ReflectUtils;
import aston.java.intensive.module5.utils.di.ServiceDescriptor;
import aston.java.intensive.module5.utils.di.ServiceProvider;
import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

public class ManuHandler implements Layer {
    @Override
    public Response invoke(MenuContext menuContext, RequestDelegate next) {
        List<Class<?>> defaultMenuClasses = ListsUtils.newArrayList();
        for (ServiceDescriptor descriptor : menuContext.serviceProvider().getDescriptors()) {

            var menuAnnotation = ReflectUtils.getAnnotation(descriptor.serviceClass(), Menu.class);
            if (menuAnnotation.isEmpty()) {
                continue;
            }
            Class<?> menuClass = descriptor.serviceClass();
            var routeMenu = menuAnnotation.get().value();
            if (routeMenu.equals(ResourceMenu.DEFAULT))
            {
                defaultMenuClasses.add(menuClass);
                continue;
            }
            if (routeMenu.equals(menuContext.request().resource().menu().value())) {
                return route(menuContext.serviceProvider(), menuContext.request(), menuClass).orElseThrow();
            }
        }

        for (Class<?> defaultMenuClass : defaultMenuClasses) {
            var menuAnnotation = ReflectUtils.getAnnotation(defaultMenuClass, Menu.class);
            var routeMenu = menuAnnotation.get().value();
            if (routeMenu.equals(menuContext.request().resource().menu().value())) {
                return route(menuContext.serviceProvider(), menuContext.request(), defaultMenuClass).orElseThrow();
            }
        }

        if (next == null) {
            return new Response(Resource.notFound());
        } else {
            return next.invoke(menuContext);
        }
    }

    private Optional<Response> route(ServiceProvider serviceProvider, Request request, Class<?> targetMenuClass) {
        var menu = serviceProvider.getService(targetMenuClass);
        if (menu.isErr()) {
            return Optional.empty();
        }
        var methods = ReflectUtils.getAnnotatedMethods(targetMenuClass, Action.class);
        Method defaultActionMethod = null;
        for (Method method : methods) {
            final String methodName = method.getName();
            var annotation = ReflectUtils.getAnnotation(method, Action.class).orElseThrow(() -> new NotSupportedException(String.format("The '%s' method not supported.", methodName)));
            if (annotation.value().equals(ResourceAction.DEFAULT))
            {
                defaultActionMethod = method;
                continue;
            }
            if (annotation.value().equals(request.resource().action().value())) {
                Object response;
                try {
                    response = method.invoke(menu.getValue(), ArrayUtils.newArray(request.param()));
                    if (response instanceof Response) {
                        return Optional.of((Response)response);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new NotSupportedException(e);
                }
            }
        }

        if (defaultActionMethod != null) {
            Object response;
            try {
                response = defaultActionMethod.invoke(menu.getValue(), ArrayUtils.newArray(request.param()));
                if (response instanceof Response) {
                    return Optional.of((Response)response);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new NotSupportedException(e);
            }
        }

        return Optional.empty();
    }
}
