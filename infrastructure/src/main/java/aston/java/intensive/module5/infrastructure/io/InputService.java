package aston.java.intensive.module5.infrastructure.io;

public interface InputService {
    int readInt();

    int readInt(String prompt);

    String readString();

    String readString(String prompt);
}
