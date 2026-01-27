package aston.java.intensive.module5.utils.json;

import aston.java.intensive.module5.utils.StringUtils;

public final class JsonString implements JsonValue {
    private static final EscaperString escaperString = getEscaperString();

    private final String value;

    public JsonString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("\"");
        String str = StringUtils.nullToEmpty(this.value);
        escaperString.escape(str);
        sb.append("\"");
        return sb.toString();
    }

    private static EscaperString getEscaperString() {
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
}
