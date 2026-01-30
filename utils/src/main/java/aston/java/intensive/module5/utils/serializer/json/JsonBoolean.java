package aston.java.intensive.module5.utils.serializer.json;

public final class JsonBoolean implements JsonValue {
    private final boolean value;

    public JsonBoolean(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Boolean.toString(this.value);
    }
}
