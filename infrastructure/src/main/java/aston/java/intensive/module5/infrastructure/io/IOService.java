package aston.java.intensive.module5.infrastructure.io;

public interface IOService extends InputService, OutputService {
    default int readIntOrDefault(int defaultValue) {
        try {
            return readInt();
        }catch (Exception e) {
            return defaultValue;
        }
    }

    default int readIntOrDefault(String prompt, int defaultValue) {
        output(prompt);
        return readIntOrDefault(defaultValue);
    }
}
