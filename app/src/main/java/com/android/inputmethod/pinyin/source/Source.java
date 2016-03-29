package com.android.inputmethod.pinyin.source;

import java.util.Hashtable;

public final class Source {
    private static Hashtable<SourceType, Hashtable<String, String>> source = new Hashtable<>();

    static {
        Hashtable <String, String> latinSource = new Hashtable<>();
        Hashtable <String, String> arabicSource = new Hashtable<>();
        source.put(SourceType.Latin, latinSource);
        source.put(SourceType.Arabic, arabicSource);
        for (Hashtable<String, String> currentSource: source.values()) {
            for (int i= 0; i < 10; i ++) {
                currentSource.put("0" + i, "" + i);

            }
            currentSource.put("99", "www");
        }
        latinSource.put("11", "a");
        latinSource.put("12", "b");
        latinSource.put("13", "c");
        latinSource.put("14", "d");
        latinSource.put("15", "e");
        latinSource.put("16", "f");
        latinSource.put("17", "g");
        latinSource.put("18", ",");
        latinSource.put("19", ".");
        latinSource.put("10", "?");

        latinSource.put("21", "h");
        latinSource.put("22", "i");
        latinSource.put("23", "j");
        latinSource.put("24", "k");
        latinSource.put("25", "l");
        latinSource.put("26", "m");
        latinSource.put("27", "n");
        latinSource.put("28", "-");
        latinSource.put("29", ";");
        latinSource.put("20", "'");

        latinSource.put("31", "o");
        latinSource.put("32", "p");
        latinSource.put("33", "q");
        latinSource.put("34", "r");
        latinSource.put("35", "s");
        latinSource.put("36", "t");
        latinSource.put("37", "[");
        latinSource.put("38", "]");
        latinSource.put("39", ";");
        latinSource.put("30", "'");

        latinSource.put("41", "u");
        latinSource.put("42", "v");
        latinSource.put("43", "w");
        latinSource.put("44", "x");
        latinSource.put("45", "y");
        latinSource.put("46", "z");
        latinSource.put("47", ",");
        latinSource.put("48", " ");
        latinSource.put("49", "./");
        latinSource.put("40", "@");

        arabicSource.put("1", "ا");
        arabicSource.put("2", "ح");
        arabicSource.put("3", "ع");
        arabicSource.put("4", "ر");
        arabicSource.put("5", "ى");
        arabicSource.put("6", "ف");
        arabicSource.put("7", "ل");
        arabicSource.put("8", "ص");
        arabicSource.put("9", "و");
        arabicSource.put("10", "ن");
        arabicSource.put("11", "س");
        arabicSource.put("12", "خ");
        arabicSource.put("13", "غ");
        arabicSource.put("14", "ز");
        arabicSource.put("15", "ئ");
        arabicSource.put("16", "ظ");
        arabicSource.put("17", "ب");
        arabicSource.put("18", "ض");
        arabicSource.put("19", "ؤ");
        arabicSource.put("20", "ت");
        arabicSource.put("21", "ش");
        arabicSource.put("22", "م");
        arabicSource.put("23", "ء");
        arabicSource.put("24", "[]");
        arabicSource.put("25", "ي");
        arabicSource.put("26", "ة");
        arabicSource.put("27", "ك");
        arabicSource.put("28", "لا");
        arabicSource.put("29", "ق");
        arabicSource.put("30", "ث");
    }

    public static String findValue(SourceType type, String code) {
        String result;
        result = source.get(type).get(code);

        if (result == null) {
            result = code;
        }
        return result;
    }
}
