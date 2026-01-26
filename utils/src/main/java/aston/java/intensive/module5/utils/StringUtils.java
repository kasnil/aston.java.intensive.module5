package aston.java.intensive.module5.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public final class StringUtils {
    private static final Pattern SLUGIFY_REGEX = Pattern.compile("[^a-zA-Z0-9\\.\\-_]+");

    public static String slugify(String txt) {
        String str = Normalizer.normalize(txt.replaceAll("\\s+", "-"), Normalizer.Form.NFD);

        return SLUGIFY_REGEX.matcher(str).replaceAll("");
    }

    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }
}
