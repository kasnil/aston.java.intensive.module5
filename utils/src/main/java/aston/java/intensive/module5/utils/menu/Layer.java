package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.menu.models.Request;
import aston.java.intensive.module5.utils.menu.models.RequestDelegate;
import aston.java.intensive.module5.utils.menu.models.Response;

public interface Layer {
    Response invoke(Request request, RequestDelegate next);
}
