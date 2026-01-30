package aston.java.intensive.module5.utils.json;

import aston.java.intensive.module5.utils.EscaperString;

import java.util.ArrayDeque;

public class JsonBuilder {
    public static final EscaperString ESCAPER_STRING = getEscaperString();
    private ArrayDeque<JsonCounter> counters;
    private ArrayDeque<JsonBuilderState> jsonBuilderStates;

    private final StringBuilder json;

    public JsonBuilder() {
        this.json = new StringBuilder();

        this.counters = new ArrayDeque<>();
        this.jsonBuilderStates = new ArrayDeque<>();

        beginScope(JsonBuilderState.UNSPECIFIED);
    }

    private JsonBuilder beginScope(JsonBuilderState state) {
        this.counters.push(new JsonCounter());

        switch (state) {
            case UNSPECIFIED -> this.jsonBuilderStates.push(JsonBuilderState.UNSPECIFIED);
            case IN_ARRAY -> this.jsonBuilderStates.push(JsonBuilderState.IN_ARRAY);
            case IN_MAP -> this.jsonBuilderStates.push(JsonBuilderState.IN_MAP);
        }
        return this;
    }

    private JsonBuilder endScope() {
        this.counters.poll();

        return this;
    }

    private JsonBuilder beginArrayScope() {
        return beginScope(JsonBuilderState.IN_ARRAY);
    }

    private JsonBuilder endArrayScope() {
        this.jsonBuilderStates.poll();
        return endScope();
    }

    private JsonBuilder beginMapScope() {
        return beginScope(JsonBuilderState.IN_MAP);
    }

    private JsonBuilder endMapScope() {
        this.jsonBuilderStates.poll();
        return endScope();
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

        beginMapScope();

        getCurrentCounterOrThrow().resetAssociativeArrayEntries();

        return append(getCurrentCounterOrThrow().incAssociativeArrayCount() > 0 ? ",".intern() : "".intern()).append("{".intern());
    }

    public JsonBuilder endAssociativeArray() {
        return append("}".intern()).endMapScope();
    }

    private JsonBuilder newArrayEntry() {
        return append(getCurrentCounterOrThrow().incArrayEntries() > 0 ? ",".intern() : "".intern());
    }

    public JsonBuilder beginArray() {
        if(getCurrentJsonBuilder() == JsonBuilderState.IN_ARRAY) {
            newArrayEntry();
        }

        beginArrayScope().getCurrentCounterOrThrow().resetArrayEntries();
        return append(getCurrentCounter().incArrayCount() > 0 ? ",".intern() : "".intern()).append("[".intern());
    }

    public void endArray() {
        append("]").endArrayScope();
    }

    public JsonBuilder addKey(String key) {
        return newAssociativeArrayEntry().quote().append(key).quote().append(":");
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

    public JsonBuilder addValue(Object obj) {
        return switch (obj) {
            case String s -> addValue(s);
            case Integer i -> addValue(i);
            case Long l -> addValue(l);
            case Double d -> addValue(d);
            case Boolean b -> addValue(b);
            default -> addValue(obj.toString());
        };
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
        IN_MAP,
    }
}
