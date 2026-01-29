package aston.java.intensive.module5.utils.json;

import java.util.ArrayDeque;
import java.util.List;

public class JsonBuilder {
    public static final EscaperString ESCAPER_STRING = getEscaperString();
    private ArrayDeque<JsonCounter> counters;
    private ArrayDeque<JsonBuilderState> jsonBuilderStates;

    private final StringBuilder json;

    public JsonBuilder() {
        this.json = new StringBuilder();

        this.counters = new ArrayDeque<>();
        this.jsonBuilderStates = new ArrayDeque<>(List.of(JsonBuilderState.UNSPECIFIED));

        beginSub();
    }

    private JsonBuilder beginSub() {
        this.counters.push(new JsonCounter());
        return this;
    }

    private JsonBuilder endSub() {
        this.counters.poll();

        return this;
    }

    private JsonBuilder beginArraySub() {
        this.jsonBuilderStates.push(JsonBuilderState.IN_ARRAY);
        return beginSub();
    }

    private JsonBuilder endArraySub() {
        this.jsonBuilderStates.poll();
        return endSub();
    }

    private JsonBuilderState getCurrentJsonBuilder() {
        JsonBuilderState state = this.jsonBuilderStates.peek();

        if(state == null) {
            return JsonBuilderState.UNSPECIFIED;
        }

        return state;
    }

    private JsonCounter getCurrentCounter() {
        return this.counters.peek();
    }

    private JsonCounter getCurrentCounterOrThrow() {
        JsonCounter counter = this.counters.peek();
        if (counter == null) {
            throw new JsonException("Ошибка формирования json: %s <", json);
        }

        return counter;
    }

    public String build() {
        return this.json.toString();
    }

    public JsonBuilder reset() {
        this.json.delete(0, this.json.length());
        return this;
    }

    public JsonBuilder quote() {
        json.append("\"".intern());
        return this;
    }

    public JsonBuilder jsonEscape(String str) {
        json.append(new JsonString(str));
        return this;
    }

    private JsonBuilder newAssociativeArrayEntry() {
        return append(getCurrentCounterOrThrow().incAssociativeArrayEntries() > 0 ? ",".intern() : "".intern());
    }

    public JsonBuilder beginAssociativeArray() {
        if(getCurrentJsonBuilder() == JsonBuilderState.IN_ARRAY) {
            newArrayEntry();
        }

        beginSub();

        getCurrentCounterOrThrow().resetAssociativeArrayEntries();

        return append(getCurrentCounterOrThrow().incAssociativeArrayCount() > 0 ? ",".intern() : "".intern()).append("{".intern());
    }

    public JsonBuilder endAssociativeArray() {
        return append("}".intern()).endSub();
    }

    private JsonBuilder newArrayEntry() {
        return append(getCurrentCounterOrThrow().incArrayEntries() > 0 ? ",".intern() : "".intern());
    }

    public JsonBuilder beginArray() {
        if(getCurrentJsonBuilder() == JsonBuilderState.IN_ARRAY) {
            newArrayEntry();
        }

        beginArraySub().getCurrentCounterOrThrow().resetArrayEntries();
        return append(getCurrentCounter().incArrayCount() > 0 ? ",".intern() : "".intern()).append("[".intern());
    }

    public void endArray() {
        append("]").endArraySub();
    }

    public JsonBuilder addKey(String key) {
        return newAssociativeArrayEntry()
                .quote()
                .jsonEscape(key)
                .quote()
                .append(": ");
    }

    private JsonBuilder append(String str) {
        json.append(str);
        return this;
    }

    private JsonBuilder append(int val) {
        json.append(val);
        return this;
    }

    private JsonBuilder append(double val) {
        json.append(val);
        return this;
    }

    private JsonBuilder append(boolean val) {
        json.append(val);
        return this;
    }

    public JsonBuilder addValue(int value) {
        if(getCurrentJsonBuilder() == JsonBuilderState.IN_ARRAY) {
            newArrayEntry();
        }

        return append(value);
    }

    public JsonBuilder addValue(double value) {
        if(getCurrentJsonBuilder() == JsonBuilderState.IN_ARRAY) {
            newArrayEntry();
        }

        return append(value);
    }

    public JsonBuilder addValue(boolean value) {
        if(getCurrentJsonBuilder() == JsonBuilderState.IN_ARRAY) {
            newArrayEntry();
        }

        return append(value);
    }

    public JsonBuilder addValue(String value) {
        if(getCurrentJsonBuilder() == JsonBuilderState.IN_ARRAY) {
            newArrayEntry();
        }

        return quote()
                .jsonEscape(value)
                .quote();
    }

    public static EscaperString getEscaperString() {
        var builder = new EscaperString.EscaperStringBuilder();
        builder.addEscape('"', "\\\"");
        builder.addEscape('\\', "\\\\");
        builder.addEscape('/', "\\/");
        builder.addEscape('\b', "\\b");
        builder.addEscape('\f', "\\f");
        builder.addEscape('\n', "\\n");
        builder.addEscape('\r', "\\r");
        builder.addEscape('\t', "\\t");
        return builder.build();
    }

    private class JsonCounter {
        private int associativeArrayEntries;
        private int associativeArrayCount;
        private int arrayCount;
        private int arrayEntries;

        public JsonCounter() {
            this.associativeArrayEntries = 0;
            this.associativeArrayCount = 0;
            this.arrayCount = 0;
            this.arrayEntries = 0;
        }

        public int incAssociativeArrayEntries() {
            return this.associativeArrayEntries++;
        }

        public void resetAssociativeArrayEntries() {
            this.associativeArrayEntries = 0;
        }

        public int incAssociativeArrayCount() {
            return this.associativeArrayCount++;
        }

        public int incArrayEntries() {
            return this.arrayEntries++;
        }

        public int incArrayCount() {
            return this.arrayCount++;
        }

        public void resetArrayEntries() {
            this.arrayEntries = 0;
        }
    }

    private enum JsonBuilderState {
        UNSPECIFIED,
        IN_ARRAY,
    }
}
