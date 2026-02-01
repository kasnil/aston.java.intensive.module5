package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.di.ServiceProvider;
import aston.java.intensive.module5.utils.guard.Ensure;
import aston.java.intensive.module5.utils.menu.models.*;

import java.util.List;
import java.util.function.Function;

public class Application {
    private final ApplicationResourceBundle resource = ApplicationResourceBundle.RESOURCES;
    private final List<Function<RequestDelegate, RequestDelegate>> layers;
    private final ServiceProvider serviceProvider;

    public Application(
            List<Function<RequestDelegate, RequestDelegate>> layers,
            ServiceProvider serviceProvider
    ) {
        Ensure.that(layers).isNotNull();
        Ensure.that(serviceProvider).isNotNull();

        this.layers = layers;
        this.serviceProvider = serviceProvider;
    }

    public void run() {
        var requestDelegate = buildDelegate();
        run(requestDelegate, getStartMenu());
    }

    private void run(RequestDelegate requestDelegate, Resource start) {
        Request request = new Request(start);
        while (true) {
            try {
                Response response = requestDelegate.invoke(new MenuContext(this.serviceProvider, request));
                if (response == null || response.resource().isExit()) {
                    break;
                }
                request = new Request(response.resource(), response.param());
            } catch (Exception e) {
                request = new Request(start);
            }
        }
    }

    private RequestDelegate buildDelegate()
    {
        RequestDelegate layer = menuContext -> new MenuNotFound().notFound(Param.empty());
        for (var c = this.layers.size() - 1; c >= 0; c--)
        {
            layer = this.layers.get(c).apply(layer);
        }

        return layer;
    }

    private Resource getStartMenu() {
        return this.resource.getStartResource(ResourceMenu.DEFAULT, ResourceAction.DEFAULT);
    }
}
