package aston.java.intensive.module5.utils.validation.validators;

public abstract class PropertyValidator<T> {
    public final String name;

    public PropertyValidator(String name) {
        this.name = name;
    }

    abstract public boolean isValid(T value);
}
