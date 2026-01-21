package aston.java.intensive.module5.utils.faker;

import java.util.Locale;

public enum DataLocale {
    Ru,
    En;

    public Locale getLocale() {
        return switch (this) {
            case Ru -> new Locale("ru");
            case En -> new Locale("en");
        };
    }
}
