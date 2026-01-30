package aston.java.intensive.module5.utils.serializer.csv;

import aston.java.intensive.module5.utils.serializer.SerializerException;

public class CsvException extends SerializerException {
    public CsvException(String message) {
        super(message);
    }

    public CsvException(String format, Object... args) {
        super(format, args);
    }

    public CsvException(Throwable cause, String format, Object... args) {
        super(cause, format, args);
    }
}
