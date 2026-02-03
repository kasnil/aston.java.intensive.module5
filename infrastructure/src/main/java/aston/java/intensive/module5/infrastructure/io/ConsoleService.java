package aston.java.intensive.module5.infrastructure.io;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleService implements IOService {
    private final PrintStream output;
    private final Scanner input;

    public ConsoleService() {
        output = System.out;
        input = new Scanner(System.in);
    }

    @Override
    public int readInt() {
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public int readIntOrDefault(int defaultValue) {
        try {
            return Integer.parseInt(input.nextLine());
        }catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public int readInt(String prompt) {
        output(prompt);
        return readInt();
    }

    @Override
    public int readIntOrDefault(String prompt, int defaultValue) {
        output(prompt);
        return readIntOrDefault(defaultValue);
    }

    @Override
    public String readString() {return input.nextLine();}

    @Override
    public String readString(String prompt) {
        output(prompt);
        return readString();
    }

    @Override
    public void output(Object message) {
        output.println(message);
    }
}
