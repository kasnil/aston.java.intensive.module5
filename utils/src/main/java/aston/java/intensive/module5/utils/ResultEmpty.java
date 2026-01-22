package aston.java.intensive.module5.utils;

import java.util.Objects;

public class ResultEmpty {
    protected final RuntimeException exception;

    public ResultEmpty(RuntimeException exception) {
        this.exception = exception;
    }

    public boolean isOk() {
        return this.exception == null;
    }

    public boolean isErr () {
        return this.exception != null;
    }

    public static ResultEmpty ok() {
        return EMPTY;
    }

    private static final ResultEmpty EMPTY = new ResultEmpty(null);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        return obj instanceof ResultEmpty other
                && Objects.equals(exception, other.exception);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(exception);
    }

    @Override
    public String toString() {
        return exception != null
                ? ("ResultEmpty[" + exception + "]")
                : "ResultEmpty.ok";
    }
}
