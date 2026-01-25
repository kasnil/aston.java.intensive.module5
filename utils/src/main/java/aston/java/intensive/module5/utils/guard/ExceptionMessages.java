package aston.java.intensive.module5.utils.guard;

public final class ExceptionMessages {
    public static final String ANY_IS_NULL = "Значение не должно быть null.";
    public static final String ANY_IS_NOT_HAS_ANNOTATION = "Тип %s не содержит аннотацию %s.";
    public static final String ANY_IS_NOT_IMPLEMENTS_INTERFACE = "Тип %s не реализует интерфейс %s.";
    public static final String ANY_IS_NOT_INTERFACE = "Тип %s не является интерфейсом.";
    public static final String STRING_IS_NULL_OR_EMPTY = "Строка не должна быть пустой или null.";
    public static final String STRING_IS_NOT_MATCH = "Значение '%s' не соответствует '%s'.";
    public static final String STRING_HAS_INCORRECT_LENGTH = "Ожидаемая длина '%d', но имеет длину '%d'.";
    public static final String STRING_HAS_TOO_SHORT_LENGTH = "Строка недостаточно длинная. Она должна быть в диапазоне от '%d' до '%d', но имеет длину '%d' символов.";
    public static final String STRING_HAS_TOO_LONG_LENGTH = "Строка слишком длинная. Она должна быть в диапазоне от '%d' до '%d', но содержит '%d' символов.";
    public static final String BOOLEAN_IS_FALSE = "Ожидалось выражение, результат которого будет true.";
    public static final String BOOLEAN_IS_TRUE = "Ожидалось выражение, результат которого будет false.";
    public static final String COLLECTION_IS_EMPTY = "Пустой список запрещен.";
    public static final String INTEGER_IS_NEGATIVE = "Значение не может быть отрицательным, но имеет значение: %d.";
}
