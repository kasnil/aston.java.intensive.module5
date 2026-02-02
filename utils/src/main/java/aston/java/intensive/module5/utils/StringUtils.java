package aston.java.intensive.module5.utils;

import java.text.Normalizer;
import java.util.Objects;
import java.util.regex.Pattern;

public final class StringUtils {
    private static final Pattern SLUGIFY_REGEX = Pattern.compile("[^a-zA-Z0-9\\.\\-_]+");

    public static String slugify(String txt) {
        String str = Normalizer.normalize(txt.replaceAll("\\s+", "-"), Normalizer.Form.NFD);

        return SLUGIFY_REGEX.matcher(str).replaceAll("");
    }

    public static String nullToEmpty(String str) {
        return Objects.isNull(str) ? "" : str;
    }

    public static boolean isNullOrEmpty(String str) {
        return Objects.isNull(str) || str.trim().length() == 0;
    }

    public static String trim(String value, char trimChar) {
        if (isNullOrEmpty(value)) {
            return value;
        }
        String trimStr = String.valueOf(trimChar);
        String trimmedStr = value;
        if (trimmedStr.startsWith(trimStr)) {
            trimmedStr = trimmedStr.substring(1);
        }

        if (trimmedStr.endsWith(trimStr)) {
            trimmedStr = trimmedStr.substring(0, trimmedStr.length() - 1);
        }
        return trimmedStr;
    }

    public static String replace(String inString, String oldPattern, String newPattern) {
        if (isNullOrEmpty(inString) || isNullOrEmpty(oldPattern) || newPattern == null) {
            return inString;
        }
        int index = inString.indexOf(oldPattern);
        if (index == -1) {
            return inString;
        }

        int capacity = inString.length();
        if (newPattern.length() > oldPattern.length()) {
            capacity += 16;
        }
        StringBuilder sb = new StringBuilder(capacity);

        int pos = 0;
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString, pos, index);
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }

        sb.append(inString, pos, inString.length());
        return sb.toString();
    }
}
