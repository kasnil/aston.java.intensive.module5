package aston.java.intensive.module5.utils.faker;

import java.util.Optional;
import java.util.ResourceBundle;

public final class Database {
    private static final String baseName = "data";

    public static Optional<String> get(String category, DataLocale dataLocale)
    {
        var locale = dataLocale.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);

        String val = bundle.getString(category);

        if (val == null)
        {
            return Optional.empty();
        }

        return Optional.of(val);
    }
}
