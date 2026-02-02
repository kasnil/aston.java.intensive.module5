package aston.java.intensive.module5.utils;

import aston.java.intensive.module5.utils.guard.Ensure;

public final class ByteUtils {
    public static byte checkedCast(int value) {
        byte result = (byte) value;
        Ensure.that(value).is((int)result, "Out of range");
        return result;
    }
}
