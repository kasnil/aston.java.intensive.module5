package aston.java.intensive.module5.utils;

import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.Map;

public final class EscaperString {
    private final Map<Character, String> replacements;

    EscaperString(Map<Character, String> replacements) {
        Ensure.that(replacements).isNotNull();
        this.replacements = replacements;
    }

    public String escape(String str) {
        StringBuilder sb = new StringBuilder();

        char[] chars = str.toCharArray();
        int length = chars.length;
        int from = 0;
        while (from < length) {
            char ch = chars[from++];
            sb.append(this.replacements.getOrDefault(ch, String.valueOf(ch)));
        }
        return sb.toString();
    }

    public static final class EscaperStringBuilder {
        private final Map<Character, String> map;

        public EscaperStringBuilder() {
            this.map = MapsUtils.newHashMap();
        }

        public EscaperStringBuilder addEscape(char c, String r) {
            Ensure.that(r).isNotNull();

            map.put(c, r);
            return this;
        }

        public EscaperStringBuilder addEscapes(char[] cs, String r) {
            Ensure.that(r).isNotNull();

            for (char c : cs) {
                addEscape(c, r);
            }
            return this;
        }

        public EscaperString build() {
            return new EscaperString(MapsUtils.newHashMap(this.map));
        }
    }
}
