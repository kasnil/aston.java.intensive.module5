package aston.java.intensive.module5.utils.json;

public final class JsonDecimal implements JsonValue {
    private final double value;

    public JsonDecimal(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }
}
