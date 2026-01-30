package aston.java.intensive.module5.utils.serializer.json;

import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {
    private int pos = 0;
    private String input;

    public JsonParser() {}

    private char current() {
        return input.charAt(this.pos);
    }

    private char next(String format, Object... args) {
        incPos();
        if (!hasNext()) {
            throw newJsonException(format, args);
        }
        return current();
    }

    private void expectNext(char chr) {
        var error = "Ожидаемый символ %c";

        var n = next(error, chr);
        if (n != chr) {
            throw newJsonException(error, chr);
        }
    }

    private void incPos() {
        this.pos++;
    }

    private boolean hasNext() {
        return this.pos < this.input.length();
    }

    private RuntimeException newJsonException(String format, Object... args) {
        return new JsonException("[%d]: %s : %s", pos, String.format(format, args), input);
    }

    private boolean hasNextOrThrow(String format, Object... args) {
        if (!hasNext()) {
            throw newJsonException(format, args);
        }

        return true;
    }

    private boolean isWhitespace(char chr) {
        return switch (chr) {
            case '\r', '\n', '\t', ' ' -> true;
            default -> false;
        };
    }

    private void skipWhitespace() {
        JsonValue result = null;

        while(hasNext() && isWhitespace(current())) {
            incPos();
        }
    }

    private boolean isDigit(char chr) {
        return '0' <= chr && chr <= '9';
    }

    private boolean isSign(char chr) {
        return switch (chr) {
            case '-', '+' -> true;
            default -> false;
        };
    }

    private boolean isDecimalSeparator(char chr) {
        return switch (chr) {
            case '.', ',' -> true;
            default -> false;
        };
    }

    private boolean isStartOfNumber(char chr) {
        return isDigit(chr) || isSign(chr);
    }

    private JsonValue parseNumber() {
        var isNumber = true;
        var builder = new StringBuilder();

        if (isSign(current())) {
            builder.append(current());
            incPos();
            hasNextOrThrow("Число не может состоять только из знака %c.", current());
        }

        if (current() == '0') {
            builder.append(current());
            incPos();

            if (hasNext() && isDecimalSeparator(current())) {
                char numberSeparator = current();
                isNumber = false;
                builder.append(current());
                incPos();

                hasNextOrThrow("Число не может заканчиваться на '%'", numberSeparator);

                if (!isDigit(current())) {
                    throw newJsonException("После десятичного разделителя должна быть как минимум одна цифра.");
                }

                while (hasNext() && isDigit(current())) {
                    builder.append(current());
                    incPos();
                }
            }
        } else {
            while (hasNext() && isDigit(current())) {
                builder.append(current());
                incPos();
            }

            if (hasNext() && isDecimalSeparator(current())) {
                char numberSeparator = current();
                isNumber = false;
                builder.append(current());
                incPos();

                hasNextOrThrow("Число не может заканчиваться на '%'", numberSeparator);

                if (!isDigit(current())) {
                    throw newJsonException("После десятичного разделителя должна быть как минимум одна цифра.");
                }

                while (hasNext() && isDigit(current())) {
                    builder.append(current());
                    incPos();
                }
            }
        }

        var value = builder.toString();
        return isNumber
                ? new JsonNumber(Long.parseLong(value))
                : new JsonDecimal(Double.parseDouble(value));
    }

    private boolean isStartOfString(char chr) {
        return chr == '"';
    }

    private JsonString parseString() {
        var missingEndChar = "Строка не завершается символом '\"";
        var builder = new StringBuilder();
        for (var c = next(missingEndChar); c != '"'; c = next(missingEndChar)) {
            if (c == '\\') {
                var n = next(missingEndChar);
                switch (n) {
                    case '"':
                        builder.append("\"");
                        break;
                    case '\\':
                        builder.append("\\");
                        break;
                    case '/':
                        builder.append("/");
                        break;
                    case 'b':
                        builder.append("\b");
                        break;
                    case 'f':
                        builder.append("\f");
                        break;
                    case 'n':
                        builder.append("\n");
                        break;
                    case 'r':
                        builder.append("\r");
                        break;
                    case 't':
                        builder.append("\t");
                        break;
                    case 'u':
                        var u1 = next(missingEndChar);
                        var u2 = next(missingEndChar);
                        var u3 = next(missingEndChar);
                        var u4 = next(missingEndChar);
                        var cp = Integer.parseInt(String.format("%c%c%c%c", u1, u2, u3, u4), 16);
                        builder.append(new String(new int[]{ cp }, 0, 1));
                        break;
                    default:
                        throw new JsonException("Неожиданный экранированный символ '%c'", n);
                }
            } else {
                builder.append(c);
            }
        }

        incPos();
        return new JsonString(builder.toString());
    }

    private boolean isStartOfBoolean(char chr) {
        return switch (chr) {
            case 't', 'f' -> true;
            default -> false;
        };
    }

    private JsonBoolean parseBoolean() {
        if (current() == 't') {
            expectNext('r');
            expectNext('u');
            expectNext('e');
            incPos();
            return new JsonBoolean(true);
        }

        if (current() == 'f') {
            expectNext('a');
            expectNext('l');
            expectNext('s');
            expectNext('e');
            incPos();
            return new JsonBoolean(false);
        }

        throw newJsonException("Логическое значение может быть только «true» или «false».");
    }

    private boolean isStartOfArray(char chr) {
        return chr == '[';
    }

    private JsonArray parseArray() {
        var error = "Массив не завершается символом ']'";
        var list = new ArrayList<JsonValue>();

        incPos();
        skipWhitespace();
        hasNextOrThrow(error);

        while (current() != ']') {
            var val = parseValue();
            list.add(val);

            hasNextOrThrow(error);
            if (current() == ',') {
                incPos();
            }
            hasNextOrThrow(error);
        }

        incPos();
        return new JsonArray(list);
    }

    private boolean isStartOfObject(char chr) {
        return chr == '{';
    }

    public JsonObject parseObject() {
        String error = "Объект не завершается символом '}'";
        Map<String, JsonValue> map = new HashMap<>();

        incPos();
        skipWhitespace();
        hasNextOrThrow(error);

        while (current() != '}') {
            if (!(parseValue() instanceof JsonString keyFieldJsonValue)) {
                throw newJsonException("Поле должно быть строкового типа.");
            }

            if (!hasNext() || current() != ':') {
                throw newJsonException("a field must be followed by ':'");
            }
            incPos();

            JsonValue valueFieldJsonValue = parseValue();
            map.put(keyFieldJsonValue.getValue(), valueFieldJsonValue);

            hasNextOrThrow(error);
            if (current() == ',') {
                incPos();
            }
            hasNextOrThrow(error);
        }

        incPos();
        return new JsonObject(map);
    }

    private JsonValue parseValue() {
        JsonValue result = null;

        skipWhitespace();
        if (hasNext()) {
            char chr = current();

            if (isStartOfNumber(chr)) {
                result = parseNumber();
            } else if (isStartOfString(chr)) {
                result = parseString();
            } else if (isStartOfBoolean(chr)) {
                result = parseBoolean();
            } else if (isStartOfArray(chr)) {
                result = parseArray();
            } else if (isStartOfObject(chr)) {
                result = parseObject();
            } else {
                throw newJsonException("Неподдерживаемое начало значения JSON");
            }
        }
        skipWhitespace();

        return result;
    }

    public JsonValue parse(final String str) {
        Ensure.that(str).isNotNullOrEmpty();

        this.pos = 0;
        this.input = str;

        var result = parseValue();
        if (hasNext()) {
            throw new JsonException("JSON может содержать только одно значение верхнего уровня.");
        }

        return result;
    }
}
