package aston.java.intensive.module5.utils.json;

import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JsonObject implements JsonValue {
    private final Map<String, JsonValue> value;

    public JsonObject(Map<String, JsonValue> value) {
        Ensure.that(value).isNotNull();

        this.value = new HashMap<>(value);
    }

    public Map<String, JsonValue> getValue() {
        return this.value;
    }

    public boolean containsKey(String key) {
        return this.value.containsKey(key);
    }

    public Optional<JsonValue> get(String key) {
        return Optional.ofNullable(this.value.get(key));
    }

    public int size() {
        return this.value.size();
    }
}
