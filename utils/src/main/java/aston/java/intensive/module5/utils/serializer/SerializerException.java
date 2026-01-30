package aston.java.intensive.module5.utils.serializer;

public class SerializerException extends RuntimeException {
    public SerializerException(String message) {
        super(message);
    }

    public SerializerException(String format, Object... args) {
        super(String.format(format, args));
    }

    public SerializerException(Throwable cause, String format, Object... args) {
        super(String.format(format, args), cause);
    }
}
