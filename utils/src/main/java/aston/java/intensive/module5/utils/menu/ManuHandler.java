package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.ArrayUtils;
import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.ReflectUtils;
import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ManuHandler implements Layer {
    private final List<Class<?>> menus;

    public ManuHandler() {

        this.menus = ListsUtils.newArrayList(MenuNotFound.class);
    }

    @Override
    public Response invoke(Request request, RequestDelegate next) {
        for (Class<?> menuClass : this.menus) {
            var menuAnnotation = ReflectUtils.getAnnotation(menuClass, Menu.class);
            if (menuAnnotation.isEmpty()) {
                continue;
            }
            var routeMenu = menuAnnotation.get().value();
            if (routeMenu.equals(ResourceMenu.DEFAULT) || routeMenu.equals(request.resource().menu().value())) {
                var menu = ReflectUtils.newInstance(menuClass);
                var methods = ReflectUtils.getAnnotatedMethods(menu.getClass(), Action.class);
                for (Method method : methods) {
                    var annotation = ReflectUtils.getAnnotation(method, Action.class).orElseThrow(() -> new NotSupportedException(String.format("The '%s' method not supported.", method.getName())));
                    if (annotation.value().equals(ResourceAction.DEFAULT) || annotation.value().equals(request.resource().action().value())) {
                        Object response = null;
                        try {
                            response = method.invoke(menu, ArrayUtils.newArray(request.param()));
                            if (response instanceof Response) {
                                return (Response)response;
                            }
                        } catch (IllegalAccessException e) {
                            throw new NotSupportedException(e);
                        } catch (InvocationTargetException e) {
                            throw new NotSupportedException(e);
                        }
                    }
                }
            }
        }
        return new Response(Resource.notFound());
    }
}
