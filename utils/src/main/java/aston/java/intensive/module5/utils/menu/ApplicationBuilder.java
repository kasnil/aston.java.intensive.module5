package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.ArrayUtils;
import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.ReflectUtils;
import aston.java.intensive.module5.utils.guard.Ensure;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Request;
import aston.java.intensive.module5.utils.menu.models.RequestDelegate;
import aston.java.intensive.module5.utils.menu.models.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ApplicationBuilder {
    private final List<Function<RequestDelegate, RequestDelegate>> layers = ListsUtils.newArrayList();
    private final List<Class<?>> menus = ListsUtils.newArrayList();

    public <T> ApplicationBuilder addMenu(Class<T> menuClass) {
        Ensure.that(menuClass).hasAnnotation(Menu.class);
        this.menus.add(menuClass);
        return this;
    }

    public <T extends Layer> ApplicationBuilder addLayer(Class<T> handlerClass) {
        Ensure.that(handlerClass).isImplementsInterface(Layer.class);
        addLayer(next -> {
            T instance = (T)ReflectUtils.newInstance(handlerClass);
            var method = ReflectUtils.getInterfaceMethod(handlerClass);
            var factory = build(method, next);
            return request -> factory.apply(instance, request);
        });
        return this;
    }

    private <T> ApplicationBuilder addLayer(Function<RequestDelegate, RequestDelegate> layer) {
        layers.add(layer);
        return this;
    }

    private <T extends Layer> BiFunction<T, Request, Response> build(Method method, RequestDelegate next) {
        return (instance, request) -> {
            try {
                var response = method.invoke(instance, ArrayUtils.newArray(request, next));
                if (response instanceof Response) {
                    return (Response)response;
                }
                throw new NotSupportedException(String.format("The '%s' method not supported.", method.getName()));
            } catch (IllegalAccessException e) {
                throw new NotSupportedException(e);
            } catch (InvocationTargetException e) {
                throw new NotSupportedException(e);
            }
        };
    }

    public Application build() {
        return new Application(layers, menus);
    }
}
