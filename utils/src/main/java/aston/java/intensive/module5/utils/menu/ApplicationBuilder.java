package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.guard.Ensure;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.RequestDelegate;

import java.util.List;
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
        return this;
    }

    private <T> ApplicationBuilder addLayer(Function<RequestDelegate, RequestDelegate> layer) {
        layers.add(layer);
        return this;
    }

    public Application build() {
        return new Application(layers, menus);
    }
}
