package aston.java.intensive.module5.utils.serializer.csv;

import aston.java.intensive.module5.utils.EscaperString;

public class CsvBuilder {
    public static final char DELIMITER = ',';
    public static final char QUOTE = '\"';
    public static final EscaperString ESCAPER_STRING = getEscaperString();

    private final StringBuilder csv;
    private StringBuilder line;

    public CsvBuilder() {
        this.csv = new StringBuilder();
    }

    public CsvBuilder beginScope() {
        this.line = new StringBuilder();

        return this;
    }

    public CsvBuilder endScope() {
        if (!this.line.isEmpty()) {
            this.csv.append(this.line);
            newLine();
        }

        return this;
    }

    private CsvBuilder append(String str) {
        line.append(str);
        return this;
    }

    private CsvBuilder append(int val) {
        line.append(val);
        return this;
    }

    private CsvBuilder append(double val) {
        line.append(val);
        return this;
    }

    private CsvBuilder append(boolean val) {
        line.append(val);
        return this;
    }

    public CsvBuilder addValue(int value) {
        addDelimiterIfNotEmpty();

        return append(value);
    }

    public CsvBuilder addValue(double value) {
        addDelimiterIfNotEmpty();

        return append(value);
    }

    public CsvBuilder addValue(boolean value) {
        addDelimiterIfNotEmpty();

        return append(value);
    }

    public CsvBuilder addValue(String value) {
        addDelimiterIfNotEmpty();

        return csvEscape(value, value.contains(String.valueOf(DELIMITER)));
    }

    public CsvBuilder addValue(Object obj) {
        return switch (obj) {
            case String s -> addValue(s);
            case Integer i -> addValue(i);
            case Long l -> addValue(l);
            case Double d -> addValue(d);
            case Boolean b -> addValue(b);
            default -> addValue(obj.toString());
        };
    }

    private CsvBuilder newLine() {
        this.csv.append("\n");

        return this;
    }

    private CsvBuilder addDelimiter() {
        this.line.append(DELIMITER);

        return this;
    }

    private CsvBuilder addDelimiterIfNotEmpty() {
        if(!this.line.isEmpty()) {
            addDelimiter();
        }

        return this;
    }

    public CsvBuilder quote() {
        this.line.append(QUOTE);
        return this;
    }

    public CsvBuilder csvEscape(String str, boolean needAddQuote) {
        if (needAddQuote) {
            quote();
        }
        this.line.append(ESCAPER_STRING.escape(str));
        if (needAddQuote) {
            quote();
        }
        return this;
    }

    public String build() {
        return this.csv.toString();
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
}
