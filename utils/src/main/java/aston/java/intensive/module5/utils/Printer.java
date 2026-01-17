package aston.java.intensive.module5.utils;

import java.io.Serializable;
import java.time.Instant;
import java.util.concurrent.Executors;

public class Printer implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String message;

    public Printer(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void printMessage() {
        var now = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            try {
                Thread.sleep(1000);
                IO.println(now);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        IO.println(this.message);
    }
}