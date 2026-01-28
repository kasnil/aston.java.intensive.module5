package aston.java.intensive.module5.application.filling.helper;


import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.Result;


import java.util.function.Function;

public final class ConsoleInputHelper {
    private final IOService console;

    public ConsoleInputHelper(IOService console) {
        this.console = console;
    }

    public <T> T readValidated(
            String prompt,
            Function<String, Result<T>> factory
    ) {
        while (true) {
            console.output(prompt);
            String input = console.readString();

            try {
                return factory.apply(input).orElseThrow();
            } catch (RuntimeException ex) {
                console.output("Ошибка: " + ex.getMessage());
            }
        }
    }
}
