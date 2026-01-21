package aston.java.intensive.module5.utils.guard;

public final class ExceptionMessages {
    public static final String ANY_IS_NULL = "Значение не должно быть null.";
    public static final String STRING_IS_NULL_OR_EMPTY = "Строка не должна быть пустой или null.";
    public static final String STRING_IS_NOT_MATCH = "Значение '%s' не соответствует '%s'";
    public static final String STRING_HAS_INCORRECT_LENGTH = "Expected length '%d' but found '%d'.";
    public static final String STRING_HAS_TOO_SHORT_LENGTH = "The string is not long enough. Must be between '%d' and '%d' but was '%d' characters long.";
    public static final String STRING_HAS_TOO_LONG_LENGTH = "The string is too long. Must be between '{0}' and  '{1}'. Must be between '%d' and '%d' but was '%d' characters long.";
    public static final String BOOLEAN_IS_FALSE = "Ожидалось выражение, результат которого будет true.";
    public static final String BOOLEAN_IS_TRUE = "Ожидалось выражение, результат которого будет false.";
    public static final String COLLECTION_IS_EMPTY = "Пустой список запрещен.";
}
