package aston.java.intensive.module5.utils.json;

import aston.java.intensive.module5.utils.StringUtils;
import aston.java.intensive.module5.utils.guard.Ensure;

public final class JsonString implements JsonValue {
    private final String value;

    public JsonString(String value) {
        Ensure.that(value).isNotNull();

        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        String str = StringUtils.nullToEmpty(this.value);
        return JsonBuilder.ESCAPER_STRING.escape(str);
    }
}
