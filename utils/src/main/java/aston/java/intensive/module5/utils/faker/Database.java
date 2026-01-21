package aston.java.intensive.module5.utils.faker;

import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

public final class Database {
    private static final String baseName = "data";

    public static Optional<String> get(String category, DataLocale dataLocale)
    {
        String val = getValue(category, dataLocale);

        if (val == null)
        {
            return Optional.empty();
        }

        return Optional.of(val);
    }

    public static boolean hasKey(String category, DataLocale dataLocale) {
        ResourceBundle bundle = getResource(dataLocale);

        return bundle.containsKey(category);
    }

    private static ResourceBundle getResource(DataLocale dataLocale) {
        var locale = dataLocale.getLocale();
        ResourceBundle resource = ResourceBundle.getBundle(baseName, locale);
        return resource;
    }

    private static String getValue(String category, DataLocale dataLocale) {
        ResourceBundle bundle = getResource(dataLocale);

        try {
            String val = bundle.getString(category);
            return val;
        } catch (MissingResourceException e) {
            return null;
        }
    }
}
