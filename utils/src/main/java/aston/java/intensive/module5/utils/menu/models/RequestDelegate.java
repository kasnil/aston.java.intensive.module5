package aston.java.intensive.module5.utils.menu.models;

@FunctionalInterface
public interface RequestDelegate{
    Response invoke(Request request);
}
