package aston.java.intensive.module5.utils.json;

public class JsonException extends RuntimeException {
    public JsonException(String message) {
        super(message);
    }

    public JsonException(String format, Object... args) {
        this(String.format(format, args));
    }
}
