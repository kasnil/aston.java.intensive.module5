package aston.java.intensive.module5.utils;

import java.util.NoSuchElementException;

public class EmptyResult {
    private final Exception exception;

    public EmptyResult(Exception exception) {
        this.exception = exception;
    }

    public boolean isOk() {
        return this.exception == null;
    }

    public boolean isErr () {
        return this.exception != null;
    }

    public static EmptyResult empty() {
        return EMPTY;
    }

    private static final EmptyResult EMPTY = new EmptyResult(null);
}
