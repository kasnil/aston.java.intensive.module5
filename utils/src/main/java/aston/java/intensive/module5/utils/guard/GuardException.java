package aston.java.intensive.module5.utils.guard;

import java.io.Serial;

public class GuardException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public GuardException(String message) {
        super(message);
    }

    public GuardException(String message, Object... args) {
        this(String.format(message, args));
    }

    public GuardException(Throwable cause) {
        super(cause);
    }
}
