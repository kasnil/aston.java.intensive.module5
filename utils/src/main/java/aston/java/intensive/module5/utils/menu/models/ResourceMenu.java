package aston.java.intensive.module5.utils.menu.models;

public record ResourceMenu(String value) {
    public static final String DEFAULT = "*";
    public static final String EXIT = "exit";

    public ResourceMenu {
        value = value == null ? DEFAULT : value;
    }

    public ResourceMenu() {
        this(DEFAULT);
    }

    public boolean isExit() {
        return this.value.equals(EXIT);
    }

    public static ResourceMenu exit() {
        return new ResourceMenu(EXIT);
    }
}
