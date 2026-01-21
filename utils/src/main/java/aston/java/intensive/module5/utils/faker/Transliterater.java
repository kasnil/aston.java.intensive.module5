package aston.java.intensive.module5.utils.faker;

public final class Transliterater {
    public static String translate(String input, DataLocale from) {
        return switch (from) {
            case En -> input;
            case Ru -> translateRuToEn(input);
        };
    }

    public static String translateRuToEn(String text) {
        StringBuilder sb = new StringBuilder((int)(text.length() * 1.3));
        char[] chars = text.toCharArray();

        for(int i = 0; i < chars.length; i++) {
            switch(chars[i]) {
                case 'а': sb.append('a'); break;
                case 'б': sb.append('b'); break;
                case 'в': sb.append('v'); break;
                case 'г': sb.append('g'); break;
                case 'д': sb.append('d'); break;
                case 'е': sb.append('e'); break;
                case 'ё': sb.append("yo"); break;
                case 'ж': sb.append("zh"); break;
                case 'з': sb.append('z'); break;
                case 'и': sb.append('i'); break;
                case 'й': sb.append('y'); break;
                case 'к': sb.append('k'); break;
                case 'л': sb.append('l'); break;
                case 'м': sb.append('m'); break;
                case 'н': sb.append('n'); break;
                case 'о': sb.append('o'); break;
                case 'п': sb.append('p'); break;
                case 'р': sb.append('r'); break;
                case 'с': sb.append('s'); break;
                case 'т': sb.append('t'); break;
                case 'у': sb.append('u'); break;
                case 'ф': sb.append('f'); break;
                case 'х': sb.append("kh"); break;
                case 'ц': sb.append("ts"); break;
                case 'ч': sb.append("ch"); break;
                case 'ш': sb.append("sh"); break;
                case 'щ': sb.append("sch"); break;
                case 'ы': sb.append('y'); break;
                case 'э': sb.append('e'); break;
                case 'ю': sb.append("yu"); break;
                case 'я': sb.append("ya"); break;

                // Верхний регистр
                case 'А': sb.append('A'); break;
                case 'Б': sb.append('B'); break;
                case 'В': sb.append('V'); break;
                case 'Г': sb.append('G'); break;
                case 'Д': sb.append('D'); break;
                case 'Е': sb.append('E'); break;
                case 'Ё': sb.append("YO"); break;
                case 'Ж': sb.append("ZH"); break;
                case 'З': sb.append('Z'); break;
                case 'И': sb.append('I'); break;
                case 'Й': sb.append('Y'); break;
                case 'К': sb.append('K'); break;
                case 'Л': sb.append('L'); break;
                case 'М': sb.append('M'); break;
                case 'Н': sb.append('N'); break;
                case 'О': sb.append('O'); break;
                case 'П': sb.append('P'); break;
                case 'Р': sb.append('R'); break;
                case 'С': sb.append('S'); break;
                case 'Т': sb.append('T'); break;
                case 'У': sb.append('U'); break;
                case 'Ф': sb.append('F'); break;
                case 'Х': sb.append("KH"); break;
                case 'Ц': sb.append("TS"); break;
                case 'Ч': sb.append("CH"); break;
                case 'Ш': sb.append("SH"); break;
                case 'Щ': sb.append("SCH"); break;
                case 'Ы': sb.append('Y'); break;
                case 'Э': sb.append('E'); break;
                case 'Ю': sb.append("YU"); break;
                case 'Я': sb.append("YA"); break;

                // Пропускаем
                case 'ъ', 'ь', 'Ъ', 'Ь': break;

                default: sb.append(chars[i]); break;
            }
        }
        return sb.toString();
    }
}
