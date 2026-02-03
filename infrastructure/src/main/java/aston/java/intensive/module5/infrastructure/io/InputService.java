package aston.java.intensive.module5.infrastructure.io;

public interface InputService {
    int readInt();

    int readIntOrDefault(int defaultValue);

    int readInt(String prompt);

    int readIntOrDefault(String prompt, int defaultValue);

    String readString();

    String readString(String prompt);
}
