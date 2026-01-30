package aston.java.intensive.module5.utils.json;

import aston.java.intensive.module5.utils.serializer.SerializerException;

public class JsonException extends SerializerException {
    public JsonException(String message) {
        super(message);
    }

    public JsonException(String format, Object... args) {
        super(format, args);
    }

    public JsonException(Throwable cause, String format, Object... args) {
        super(cause, format, args);
    }
}
