package aston.java.intensive.module5.presentation.menu.view;

import aston.java.intensive.module5.utils.menu.models.Resource;

import java.util.function.BooleanSupplier;

public final class MenuItem {

    private final String text;
    private final Resource resource;
    private final BooleanSupplier enabled;

    public MenuItem(String text, Resource resource, BooleanSupplier enabled) {
        this.text = text;
        this.resource = resource;
        this.enabled = enabled;
    }

    public MenuItem(String text, Resource resource) {
        this(text, resource, () -> true);
    }

    public String text() {
        return text;
    }

    public Resource resource() {
        return resource;
    }

    public boolean enabled() {
        return enabled.getAsBoolean();
    }
}

