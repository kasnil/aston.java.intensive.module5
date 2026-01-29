package aston.java.intensive.module5.utils.json;

import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonArray implements JsonValue, Iterable<JsonValue> {
    private final List<JsonValue> values;

    public JsonArray(List<JsonValue> values) {
        Ensure.that(values).isNotNull();

        this.values = new ArrayList<>(values.size());
        addAll(values);
    }

    public void add(JsonValue value) {
        if (value instanceof JsonArray jsonArray) {
            jsonArray.values.forEach(this::add);
        } else {
            this.values.add(value);
        }
    }

    public void addAll(List<JsonValue> values) {
        values.forEach(this::add);
    }

    public int size() {
        return this.values.size();
    }

    public List<JsonValue> value() {
        return List.copyOf(values);
    }

    @Override
    public Iterator<JsonValue> iterator() {
        return this.values.iterator();
    }
}
