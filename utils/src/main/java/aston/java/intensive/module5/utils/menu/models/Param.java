package aston.java.intensive.module5.utils.menu.models;

public record Param(String message) {
    private static final Param EMPTY = new Param(null);

    public static Param empty() {
        return EMPTY;
    }
}
