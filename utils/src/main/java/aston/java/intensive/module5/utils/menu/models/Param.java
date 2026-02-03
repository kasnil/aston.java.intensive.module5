package aston.java.intensive.module5.utils.menu.models;

public record Param(Object data) {
    private static final Param EMPTY = new Param(null);

    public static Param empty() {
        return EMPTY;
    }
}
