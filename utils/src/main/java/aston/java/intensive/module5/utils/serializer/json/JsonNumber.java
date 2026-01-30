package aston.java.intensive.module5.utils.serializer.json;

public final class JsonNumber implements JsonValue {
    private final long value;

    public JsonNumber(long value) {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Long.toString(this.value);
    }
}
