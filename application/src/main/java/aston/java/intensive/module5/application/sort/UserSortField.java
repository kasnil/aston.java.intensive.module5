package aston.java.intensive.module5.application.sort;

public enum UserSortField {
    NAME("Имя"),
    EMAIL("Email"),
    PASSWORD("Пароль");

    private final String displayName;

    UserSortField(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
