package aston.java.intensive.module5.utils.menu;

import aston.java.intensive.module5.utils.di.ServiceProvider;
import aston.java.intensive.module5.utils.menu.models.Request;

public record MenuContext(ServiceProvider serviceProvider, Request request) { }
