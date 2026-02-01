package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.menu.models.RequestDelegate;
import aston.java.intensive.module5.utils.menu.models.Response;

@FunctionalInterface
public interface Layer {
    Response invoke(MenuContext menuContext, RequestDelegate next);
}
