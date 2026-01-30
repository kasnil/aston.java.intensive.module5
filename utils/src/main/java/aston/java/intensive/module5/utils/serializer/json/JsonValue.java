package aston.java.intensive.module5.utils.serializer.json;

import java.util.Optional;

public interface JsonValue {
    default Optional<JsonString> asJsonString() {
        if (this instanceof JsonString value) {
            return Optional.of(value);
        }
        return Optional.empty();
    }

    default Optional<JsonNumber> asJsonNumber() {
        if (this instanceof JsonNumber value) {
            return Optional.of(value);
        }
        return Optional.empty();
    }

    default Optional<JsonDecimal> asJsonDecimal() {
        if (this instanceof JsonDecimal value) {
            return Optional.of(value);
        }
        return Optional.empty();
    }

    default Optional<JsonBoolean> asJsonBoolean() {
        if (this instanceof JsonBoolean value) {
            return Optional.of(value);
        }
        return Optional.empty();
    }

    default Optional<JsonArray> asJsonArray() {
        if (this instanceof JsonArray value) {
            return Optional.of(value);
        }
        return Optional.empty();
    }

    default Optional<JsonObject> asJsonObject() {
        if (this instanceof JsonObject value) {
            return Optional.of(value);
        }
        return Optional.empty();
    }
}
