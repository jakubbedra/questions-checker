package com.eti.pg.questions.checker.comparator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DamerauLevenshteinDistanceComparator {

    public int compare(String a, String b) {
        int[][] d = new int[a.length() + 2][b.length() + 2];
        Map<Character, Integer> da = new HashMap<>();

        int maxdist = a.length() + b.length();

        d[0][0] = maxdist;

        for (int i = 1; i <= a.length() + 1; i++) {
            d[i][0] = maxdist;
            d[i][1] = i - 1;
        }

        for (int j = 1; j <= b.length() + 1; j++) {
            d[0][j] = maxdist;
            d[1][j] = j - 1;
        }

        for (int i = 1; i <= a.length(); i++) {
            int db = 0;
            for (int j = 1; j <= b.length(); j++) {
                int k = da.getOrDefault(b.charAt(j - 1), 0);
                int l = db;

                int cost;
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    cost = 0;
                    db = j;
                } else {
                    cost = 1;
                }

                d[i + 1][j + 1] = Math.min(
                        d[i][j] + cost,           // substitution
                        Math.min(d[i + 1][j] + 1,  // insertion
                                Math.min(d[i][j + 1] + 1,  // deletion
                                        k > 0 && l > 0 ? d[k][l] + (i - k - 1) + 1 + (j - l - 1) : maxdist)));  // transposition
            }
            da.put(a.charAt(i - 1), i);
        }

        return d[a.length() + 1][b.length() + 1];
    }

//    public int compare(String text1, String text2) {
//        int alphabetSize = computeAlphabetSize(text1.toCharArray(), text2.toCharArray());
//        int[] da = new int[alphabetSize];
//        Map<Character, Integer> charsToInts = new HashMap<>();
//        int currentChar = 0;
//        for (char c : text1.toCharArray()) {
//            if (!charsToInts.containsKey(c)){
//                charsToInts.put(c, currentChar);
//                currentChar++;
//            }
//        }
//        for (char c : text2.toCharArray()) {
//            if (!charsToInts.containsKey(c)){
//                charsToInts.put(c, currentChar);
//                currentChar++;
//            }
//        }
//
//        for (int i = 0; i < alphabetSize; i++) {
//            da[i] = 0;
//        }
//
//        int[][] d = new int[text1.length() + 2][text2.length() + 2];
//        d[0][0] = Integer.MAX_VALUE;
//
//        for (int i = 1; i < text1.length(); i++) {
//            d[i][0] = Integer.MAX_VALUE;
//            d[i][1] = i;
//        }
//        for (int i = 1; i < text2.length(); i++) {
//            d[0][i] = Integer.MAX_VALUE;
//            d[1][i] = i;
//        }
//
//        for (int i = 2; i < text1.length(); i++) {
//            int db = 0;
//            for (int j = 2; j < text2.length(); j++) {
//                int k = da[charsToInts.get(text2.charAt(j-2))];
//                int l = db;
//                int cost = 0;
//                if (text1.charAt(i-2) == text2.charAt(j-2)) {
//                    cost = 0;
//                    db = j;
//                } else {
//                    cost = 1;
//                }
//                int min1 = Math.min(d[i-2][j-2]+cost, d[i-1][j-2]+1);
//                int min2 = Math.min(d[i-2][j-1]+1, d[k-2][l-1] + (i-k-3) + 1 + (j-l-3));
//                d[i][j] = Math.min(min1, min2);
//            }
//            da[charsToInts.get(text1.charAt(i-2))] = i-2;
//        }
//
//        return d[text1.length()][text2.length()];
//    }

    private int computeAlphabetSize(char[] chars1, char[] chars2) {
        Set<Character> chars = new HashSet<>();
        for (char c : chars1) {
            chars.add(c);
        }
        for (char c : chars2) {
            chars.add(c);
        }
        return chars.size();
    }

}
