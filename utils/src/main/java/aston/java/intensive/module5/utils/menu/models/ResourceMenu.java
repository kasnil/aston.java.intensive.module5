package aston.java.intensive.module5.utils.menu.models;

public record ResourceMenu(String value) {
    public static final String DEFAULT = "*";
    public static final String EXIT = "exit";
    public static final String NOT_FOUND = "not-found";

    public ResourceMenu {
        value = value == null ? DEFAULT : value;
    }

    public ResourceMenu() {
        this(DEFAULT);
    }

    public boolean isDefault() {
        return this.value.equals(DEFAULT);
    }

    public boolean isExit() {
        return this.value.equals(EXIT);
    }

    public boolean isNotFound() {
        return this.value.equals(NOT_FOUND);
    }

    public static ResourceMenu exit() {
        return new ResourceMenu(EXIT);
    }

    public static ResourceMenu notFound() {
        return new ResourceMenu(NOT_FOUND);
    }
}
