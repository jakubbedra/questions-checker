package com.eti.pg.questions.checker.comparator;

import java.util.*;

public class Bm25Comparator extends BaseSimpleComparator {
    private double k = 1.25;
    private double b = 0.75;

    @Override
    public double compare(String[] text1, String[] text2) {
        Set<String> words = textsToSet(text1, text2);
        double[][] similarityVec = initSimilarityVector(words);
        int i = 0;

        for (String word : words) {
            similarityVec[0][i] = bm25(word, text1, text2);
            similarityVec[1][i] = bm25(word, text2, text1);
            i++;
        }

        return cosineSimilarity(similarityVec, words.size());
    }

    private double bm25(String word, String[] text1, String[] text2) {
        double text1LengthDouble = ((double) text1.length);
        double text2LengthDouble = ((double) text2.length);
        double averageTextLength = (text1LengthDouble + text2LengthDouble) / 2.0;
        double wordOccurrenceText1 = ((double) Arrays.stream(text1).filter(w -> w.equals(word)).count());
        double nQ = 1.0 + (Arrays.asList(text2).contains(word) ? 1.0 : 0.0);

        double tf = (wordOccurrenceText1 * (k + 1.0)) /
                (text1LengthDouble + k * (1 - b + b * (text1LengthDouble / averageTextLength)));

        double idf = Math.log((TEXTS_COUNT - nQ + 0.5) / (nQ + 0.5) + 1.0);

        return tf * idf;
    }

}
