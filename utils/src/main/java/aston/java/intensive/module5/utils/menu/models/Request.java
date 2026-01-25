package aston.java.intensive.module5.utils.menu.models;

import aston.java.intensive.module5.utils.guard.Ensure;

public record Request(Resource resource, Param param) {
    public Request {
        Ensure.that(resource).isNotNull();

        param = param == null ? Param.empty() : param;
    }

    public Request(Resource resource) {
        this(resource, null);
    }
}
