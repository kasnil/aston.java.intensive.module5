package aston.java.intensive.module5.utils.csv;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CsvParserTest {
    @Test
    public void testParseWithHeader() {
        String str = """
                a,b,c
                1,2,3
                "Aston,Kontur",Рога и копыта,"Андрей Бреслав"
                """;
        Map<String, List<String>> result = new CsvParser().parse(str, true);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsKey("a"));
        assertTrue(result.containsKey("b"));
        assertTrue(result.containsKey("c"));
    }

    @Test
    public void testParseWithoutHeader() {
        String str = """
                1,2,3
                "Aston,Kontur",Рога и копыта,"Андрей Бреслав"
                """;
        Map<String, List<String>> result = new CsvParser().parse(str, false);

        assertNotNull(result);
        assertEquals(3, result.size());
    }
}
