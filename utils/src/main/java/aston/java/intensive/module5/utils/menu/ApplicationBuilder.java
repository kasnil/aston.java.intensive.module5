package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.ArrayUtils;
import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.NotSupportedException;
import aston.java.intensive.module5.utils.ReflectUtils;
import aston.java.intensive.module5.utils.di.ServiceCollection;
import aston.java.intensive.module5.utils.di.ServiceLocator;
import aston.java.intensive.module5.utils.di.ServiceProvider;
import aston.java.intensive.module5.utils.di.ServiceProviderImpl;
import aston.java.intensive.module5.utils.guard.Ensure;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Request;
import aston.java.intensive.module5.utils.menu.models.RequestDelegate;
import aston.java.intensive.module5.utils.menu.models.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ApplicationBuilder {
    private final ServiceLocator serviceLocator = new ServiceLocator();
    private final List<Function<RequestDelegate, RequestDelegate>> layers = ListsUtils.newArrayList();
    private final Class<?> primarySource;

    public ApplicationBuilder(Class<?> primarySource) {
        Ensure.that(primarySource).isNotNull();
        this.primarySource = primarySource;
    }

    public <T> ApplicationBuilder addMenu(Class<T> menuClass) {
        Ensure.that(menuClass).hasAnnotation(Menu.class);
        this.serviceLocator.addSingleton(menuClass);
        return this;
    }

    public <T extends Layer> ApplicationBuilder addLayer(Class<T> handlerClass) {
        Ensure.that(handlerClass).isImplementsInterface(Layer.class);

        this.serviceLocator.addSingleton(handlerClass);

        addLayer(next -> {
            var method = ReflectUtils.getInterfaceMethod(handlerClass, Layer.class).orElseThrow(() -> new NotSupportedException(String.format("The '%s' handler not supported.", handlerClass.getName())));
            var factory = build(method, next);
            return request -> factory.apply(handlerClass, request);
        });
        return this;
    }

    public ApplicationBuilder configureServices(Consumer<ServiceCollection> services) {
        services.accept(this.serviceLocator.getServices());
        return this;
    }

    private <T> ApplicationBuilder addLayer(Function<RequestDelegate, RequestDelegate> layer) {
        layers.add(layer);
        return this;
    }

    private <T extends Layer> BiFunction<Class<?>, MenuContext, Response> build(Method method, RequestDelegate next) {
        return (handlerClass, request) -> {
            try {
                var instance = request.serviceProvider().getService(handlerClass).orElseThrow();
                var response = method.invoke(instance, ArrayUtils.newArray(request, next));
                if (response instanceof Response) {
                    return (Response)response;
                }
                throw new NotSupportedException(String.format("The '%s' method not supported.", method.getName()));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new NotSupportedException(e);
            }
        };
    }

    public Application build() {
        addLayer(ManuHandler.class);

        var serviceProvider = new ServiceProviderImpl.Builder()
                .setServiceCollection(serviceLocator.getServices())
                .build();

        return new Application(layers, serviceProvider, primarySource.getClassLoader());
    }
}
