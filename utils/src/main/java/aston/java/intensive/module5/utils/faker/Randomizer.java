package aston.java.intensive.module5.utils.faker;

import aston.java.intensive.module5.utils.guard.Ensure;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public final class Randomizer {
    public Random seed = new Random();

    public <T extends Enum<T>> T enumValue(Class<T> enumClass) {
        var enumConstants = enumClass.getEnumConstants();
        Ensure.that(enumConstants).hasItems();
        var val = arrayElement(enumConstants);
        return val;
    }

    public <T> T arrayElement(T[] array) {
        var r = number(array.length);
        return array[r];
    }

    public int number(int max)
    {
        return seed.nextInt(max);
    }

    public int number(int min, int max)
    {
        return seed.nextInt(max - min + 1) + min;
    }

    public String password(int length)
    {
        byte[] ascii = new byte[length];
        for (int i = 0; i < length; i++) {
            ascii[i] = (byte)number(33, 127); // ASCII
        }
        return new String(ascii, StandardCharsets.US_ASCII);
    }
}
