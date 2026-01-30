package aston.java.intensive.module5.utils.csv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvBuilderTest {
    @Test
    public void testCsvBuild() {
        CsvBuilder builder = new CsvBuilder();
        builder.beginScope();
        builder.addValue("company");
        builder.addValue("phone");
        builder.endScope();
        builder.beginScope();
        builder.addValue("Aston");
        builder.addValue("+7 999 66-66-66");
        builder.endScope();
        builder.beginScope();
        builder.addValue("Kontur");
        builder.addValue("+7 999 77-77-77");
        builder.endScope();

        String json = builder.build();

        assertEquals("""
                company,phone
                Aston,+7 999 66-66-66
                Kontur,+7 999 77-77-77
                """, json);
    }
}
