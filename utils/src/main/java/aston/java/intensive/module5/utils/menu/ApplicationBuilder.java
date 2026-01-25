package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.guard.Ensure;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.RequestDelegate;

import java.util.List;
import java.util.function.Function;

public class ApplicationBuilder {
    private final List<Function<RequestDelegate, RequestDelegate>> handlers = ListsUtils.newArrayList();

    public <T> ApplicationBuilder addMenu(Class<T> handlerClass) {
        Ensure.that(handlerClass).hasAnnotation(Menu.class);
        return this;
    }

    private <T> ApplicationBuilder addMenu(Function<RequestDelegate, RequestDelegate> handler) {
        handlers.add(handler);
        return this;
    }

    public Application build() {
        return new Application();
    }
}
