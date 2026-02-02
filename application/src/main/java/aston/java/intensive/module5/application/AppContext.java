package aston.java.intensive.module5.application;

public final class AppContext {
    private static UserService userService;

    public static void init(UserService service) {
        userService = service;
    }

    public static UserService userService() {
        if (userService == null) {
            throw new IllegalStateException("AppContext not initialized");
        }
        return userService;
    }

    private AppContext() {}
}
