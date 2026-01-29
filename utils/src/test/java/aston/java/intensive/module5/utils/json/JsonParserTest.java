package aston.java.intensive.module5.utils.json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {
    @ParameterizedTest
    @ValueSource(strings = { "", "Aston" })
    public void testJsonString(String value) {
        JsonValue jsonValue = new JsonParser().parse("\"" + value + "\"");

        assertTrue(jsonValue instanceof JsonString);
        assertEquals(value, ((JsonString) jsonValue).getValue());
    }

    @ParameterizedTest
    @ValueSource(longs = { 292 })
    public void testJsonNumber(long value) {
        JsonValue jsonValue = new JsonParser().parse(Long.toString(value));

        assertTrue(jsonValue instanceof JsonNumber);
        assertEquals(value, ((JsonNumber) jsonValue).getValue());
    }

    @ParameterizedTest
    @ValueSource(doubles = { 292.12 })
    public void testJsonDecimal(double value) {
        JsonValue jsonValue = new JsonParser().parse(Double.toString(value));

        assertTrue(jsonValue instanceof JsonDecimal);
        assertEquals(value, ((JsonDecimal) jsonValue).getValue());
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void testJsonBoolean(boolean value) {
        JsonValue jsonValue = new JsonParser().parse(Boolean.toString(value));

        assertTrue(jsonValue instanceof JsonBoolean);
        assertEquals(value, ((JsonBoolean) jsonValue).getValue());
    }

    @Test
    public void testJsonObject() {
        String str = """
                {
                    "company": {
                        "name": "Aston"
                    }
                }""";
        JsonValue jsonValue = new JsonParser().parse(str);

        assertTrue(jsonValue instanceof JsonObject);
        JsonObject jsonObject = (JsonObject) jsonValue;
        assertTrue(jsonObject.get("company").isPresent());
        assertTrue(jsonObject.get("company").get() instanceof JsonObject);
        JsonObject jsonObjectCompany = (JsonObject) jsonObject.get("company").get();
        assertTrue(jsonObjectCompany.get("name").isPresent());
        assertTrue(jsonObjectCompany.get("name").get() instanceof JsonString);
        assertEquals("Aston", ((JsonString) jsonObjectCompany.get("name").get()).getValue());
    }
}
