package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.menu.models.RequestDelegate;
import aston.java.intensive.module5.utils.menu.models.Resource;

import java.util.List;
import java.util.function.Function;

public class Application {
    private final List<Function<RequestDelegate, RequestDelegate>> layers;
    private final List<Class<?>> menus;

    public Application(
            List<Function<RequestDelegate, RequestDelegate>> layers,
            List<Class<?>> menus
    ) {
        this.layers = layers;
        this.menus = menus;
    }

    public void run(Resource start) {
        while (true) {

        }
    }

    private void run(RequestDelegate request) {

    }
}
