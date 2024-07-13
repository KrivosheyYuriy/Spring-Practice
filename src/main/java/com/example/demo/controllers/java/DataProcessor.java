package com.example.demo.controllers.java;

public class DataProcessor {
    public static String capitalize(String str, String delimiter) {
        String[] words = str.split(delimiter);
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return String.join(delimiter, words);
    }
}
