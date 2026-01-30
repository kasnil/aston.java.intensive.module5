package aston.java.intensive.module5.utils.serializer.json;

import aston.java.intensive.module5.utils.serializer.json.JsonBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonBuilderTest {
    @Test
    public void testEmptyJsonBuild() {
        JsonBuilder builder = new JsonBuilder();
        builder.beginAssociativeArray();
        builder.endAssociativeArray();

        String json = builder.build();

        assertEquals("{}", json);
    }

    @Test
    public void testJsonNumberJsonBuild() {
        JsonBuilder builder = new JsonBuilder();
        builder.beginAssociativeArray();
        builder.addKey("value");
        builder.addValue(292);
        builder.endAssociativeArray();

        String json = builder.build();

        assertEquals("{\"value\":292}", json);
    }

    @Test
    public void testJsonDoubleJsonBuild() {
        JsonBuilder builder = new JsonBuilder();
        builder.beginAssociativeArray();
        builder.addKey("value");
        builder.addValue(292.23);
        builder.endAssociativeArray();

        String json = builder.build();

        assertEquals("{\"value\":292.23}", json);
    }

    @Test
    public void testJsonStringJsonBuild() {
        JsonBuilder builder = new JsonBuilder();
        builder.beginAssociativeArray();
        builder.addKey("name");
        builder.addValue("Aston");
        builder.endAssociativeArray();

        String json = builder.build();

        assertEquals("{\"name\":\"Aston\"}", json);
    }

    @Test
    public void testJsonBooleanJsonBuild() {
        JsonBuilder builder = new JsonBuilder();
        builder.beginAssociativeArray();
        builder.addKey("value");
        builder.addValue(true);
        builder.endAssociativeArray();

        String json = builder.build();

        assertEquals("{\"value\":true}", json);
    }

    @Test
    public void testJsonObjectJsonBuild() {
        JsonBuilder builder = new JsonBuilder();
        builder.beginAssociativeArray();
        builder.addKey("company");
        builder.beginAssociativeArray();
        builder.addKey("name");
        builder.addValue("Aston");
        builder.endAssociativeArray();
        builder.endAssociativeArray();

        String json = builder.build();

        assertEquals("{\"company\":{\"name\":\"Aston\"}}", json);
    }

    @Test
    public void testJsonArrayJsonBuild() {
        JsonBuilder builder = new JsonBuilder();
        builder.beginAssociativeArray();
        builder.addKey("companies");
        builder.beginArray();
        builder.beginAssociativeArray();
        builder.addKey("name");
        builder.addValue("Aston");
        builder.endAssociativeArray();
        builder.beginAssociativeArray();
        builder.addKey("name");
        builder.addValue("Kontur");
        builder.endAssociativeArray();
        builder.endArray();
        builder.addKey("key1");
        builder.addValue("value1");
        builder.addKey("key2");
        builder.addValue("value2");
        builder.endAssociativeArray();

        String json = builder.build();

        assertEquals("{\"companies\":[{\"name\":\"Aston\"},{\"name\":\"Kontur\"}],\"key1\":\"value1\",\"key2\":\"value2\"}", json);
    }
}
