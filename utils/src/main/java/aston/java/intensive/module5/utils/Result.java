package aston.java.intensive.module5.utils;

import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.function.Consumer;

public class Result<T> extends EmptyResult {
    private final T value;

    public Result(T value, Exception exception) {
        super(exception);
        this.value = value;
    }

    public T getValue() {
        Ensure.that(this.value).isNotNull();
        return this.value;
    }

    public void ifOk(Consumer<? super T> action) {
        if (this.value != null) {
            action.accept(value);
        }
    }

    public void ifOkOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (this.value != null) {
            action.accept(value);
        } else {
            emptyAction.run();
        }
    }

    public static <T> Result<T> ok(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> err(Exception exception) {
        return new Result<>(null, exception);
    }
}
