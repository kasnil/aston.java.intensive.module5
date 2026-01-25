package aston.java.intensive.module5.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterTest {
    @Test
    public void testMessage() {
        var message = "message";
        var testPrinter = new Printer(message);
        assertEquals(message, testPrinter.getMessage());
    }

    @Test
    public void testSerialization() {
        var message = "message";
        var bos1 = new ByteArrayOutputStream();
        var bos2 = new ByteArrayOutputStream();

        byte[] byteArray1, byteArray2;
        try (var out1 = new ObjectOutputStream(bos1);
             var out2 = new ObjectOutputStream(bos2)) {
            out1.writeObject(new Printer(message));
            out1.flush();
            byteArray1 = bos1.toByteArray();

            out2.writeObject(new Printer(message));
            out2.flush();
            byteArray2 = bos2.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        assertArrayEquals(byteArray1, byteArray2);
    }
}