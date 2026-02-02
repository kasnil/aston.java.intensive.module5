package aston.java.intensive.module5.utils.menu.models;

import aston.java.intensive.module5.utils.menu.MenuContext;

@FunctionalInterface
public interface RequestDelegate{
    Response invoke(MenuContext menuContext);
}
