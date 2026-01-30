package aston.java.intensive.module5.utils.serializer.csv;

import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.StringUtils;
import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvParser {
    public Map<String, List<String>> parse(String csv, boolean withHeader) {
        final Map<String, List<String>> result = new HashMap<>();
        final var lines = Arrays.stream(csv.trim().split("\n"))
                .collect(Collectors.toCollection(LinkedList::new));

        final List<String> headers = ListsUtils.newArrayList();
        if (withHeader) {
            for (String header : getHeader(lines.poll())) {
                headers.add(header);
                result.put(header, ListsUtils.newArrayList());
            }
        }

        for (var i = 0; i < lines.size(); i++) {
            var fieldList = splitLine(lines.get(i));
            for (int j = 0; j < fieldList.size(); j++) {
                if (withHeader) {
                    if (j >= headers.size()) {
                        throw new CsvException("Колличество полей в строке '%d' больше разрешенных в заголовке", i + 2);
                    }
                    result.get(headers.get(j)).add(parseString(fieldList.get(j)));
                } else {
                    result.computeIfAbsent(String.valueOf(j), k -> ListsUtils.newArrayList()).add(parseString(fieldList.get(j)));
                }
            }
        }

        return result;
    }

    private List<String> splitLine(String line) {
        int ptr = 0;
        ArrayList<String> fieldList = ListsUtils.newArrayList();
        while (ptr < line.length()) {
            if (line.charAt(ptr) != CsvBuilder.QUOTE) {
                int count = 0;
                char[] chars = new char[line.length()];
                while (ptr < line.length() && line.charAt(ptr) != CsvBuilder.DELIMITER) {
                    chars[count++] = line.charAt(ptr++);
                }
                fieldList.add(parseString(new String(chars, 0, count)));
                ptr++;
            }

            else {
                ptr++;
                int count = 0;
                char[] chars = new char[line.length()];
                while (ptr < line.length()) {
                    if (line.charAt(ptr) != CsvBuilder.QUOTE) {
                        chars[count++] = line.charAt(ptr++);
                    }

                    else {

                        if (ptr + 1 < line.length()
                                && line.charAt(ptr + 1) == CsvBuilder.QUOTE)
                        {
                            chars[count++] = CsvBuilder.QUOTE;
                            ptr += 2;
                        }

                        else {
                            fieldList.add(parseString(new String(chars, 0, count)));
                            while (ptr < line.length() && line.charAt(ptr) != CsvBuilder.DELIMITER) {
                                ptr++;
                            }
                            ptr++;
                            break;
                        }
                    }
                }
            }
        }

        return List.copyOf(fieldList);
    }

    private String parseString(String value) {
        if (StringUtils.isNullOrEmpty(value)) {
            return value;
        }

        StringBuilder sb = new StringBuilder();
        char[] chars = value.toCharArray();
        int length = chars.length;
        int from = 0;
        while (from < length) {
            char current = chars[from++];
            if (current == '\\') {
                if (from < length) {
                    char next = chars[from++];
                    switch (next) {
                        case '"':
                            sb.append("\"");
                            break;
                        case '\\':
                            sb.append("\\");
                            break;
                        case '/':
                            sb.append("/");
                            break;
                        case 'b':
                            sb.append("\b");
                            break;
                        case 'f':
                            sb.append("\f");
                            break;
                        case 'n':
                            sb.append("\n");
                            break;
                        case 'r':
                            sb.append("\r");
                            break;
                        case 't':
                            sb.append("\t");
                            break;
                        case 'u':
                            var u1 = chars[from + 2];
                            var u2 = chars[from + 3];
                            var u3 = chars[from + 4];
                            var u4 = chars[from + 5];
                            var cp = Integer.parseInt(String.format("%c%c%c%c", u1, u2, u3, u4), 16);
                            sb.append(new String(new int[]{cp}, 0, 1));
                            break;
                        default:
                            throw new CsvException("Неожиданный экранированный символ '%c'", next);
                    }
                } else {
                    throw new CsvException("Неожиданный окончание строки");
                }
            }
            else {
                sb.append(current);
            }
        }
        return sb.toString();
    }

    private static List<String> getHeader(String row) {
        Ensure.that(row).isNotNull();
        return List.of(row.split(","));
    }
}