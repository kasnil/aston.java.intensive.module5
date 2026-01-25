package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.guard.Ensure;
import aston.java.intensive.module5.utils.menu.models.*;

import java.util.List;
import java.util.function.Function;

public class Application {
    private final List<Function<RequestDelegate, RequestDelegate>> layers;

    public Application(
            List<Function<RequestDelegate, RequestDelegate>> layers
    ) {
        Ensure.that(layers).isNotNull();

        this.layers = layers;
    }

    public void run(Resource start) {
        var requestDelegate = buildDelegate();
        run(requestDelegate, start);
    }

    private void run(RequestDelegate requestDelegate, Resource start) {
        Request request = new Request(start);
        while (true) {
            Response response = requestDelegate.invoke(request);
            if (response == null || response.resource().isExit()) {
                break;
            }
            request = new Request(response.resource(), response.param());
        }
    }

    private RequestDelegate buildDelegate()
    {
        RequestDelegate layer = request -> new MenuNotFound().notFound(Param.empty());
        for (var c = this.layers.size() - 1; c >= 0; c--)
        {
            layer = this.layers.get(c).apply(layer);
        }

        return layer;
    }
}
