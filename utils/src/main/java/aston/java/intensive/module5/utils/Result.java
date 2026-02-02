package aston.java.intensive.module5.utils;

import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Result<T> extends ResultEmpty {
    private final T value;

    public Result(T value, RuntimeException exception) {
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

    public T orElseThrow() {
        if (value == null) {
            throw this.exception;
        }
        return value;
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        return value != null ? value : supplier.get();
    }

    public static <T> Result<T> ok(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> err(RuntimeException exception) {
        return new Result<>(null, exception);
    }

    public Optional<T> toOptional() {
        return Optional.ofNullable(this.value);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        return obj instanceof Result<?> other
                && Objects.equals(exception, other.exception)
                && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exception, value);
    }

    @Override
    public String toString() {
        return value != null
                ? ("Result[" + value + "]")
                : ("Result[" + exception + "]");
    }
}
