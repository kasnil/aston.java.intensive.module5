package aston.java.intensive.module5.utils.menu.models;

public record ResourceAction(String value) {
    public static final String DEFAULT = "*";

    public ResourceAction {
        value = value == null ? DEFAULT : value;
    }

    public ResourceAction() {
        this(DEFAULT);
    }
}

