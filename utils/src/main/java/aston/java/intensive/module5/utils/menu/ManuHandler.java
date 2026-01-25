package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.menu.models.Request;
import aston.java.intensive.module5.utils.menu.models.RequestDelegate;
import aston.java.intensive.module5.utils.menu.models.Response;

public class ManuHandler implements Layer {
    @Override
    public Response invoke(Request request, RequestDelegate next) {
        return new Response(request.resource(), request.param());
    }
}
