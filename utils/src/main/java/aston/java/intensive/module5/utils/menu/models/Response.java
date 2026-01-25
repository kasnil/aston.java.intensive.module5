package aston.java.intensive.module5.utils.menu.models;

import aston.java.intensive.module5.utils.guard.Ensure;

public record Response(Resource resource, Param param) {
    public Response {
        Ensure.that(resource).isNotNull();

        param = param == null ? Param.empty() : param;
    }

    public Response(Resource resource) {
        this(resource, null);
    }

    public static Response exit() {
        return new Response(Resource.exit());
    }
}
