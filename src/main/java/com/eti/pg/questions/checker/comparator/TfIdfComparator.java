package com.eti.pg.questions.checker.comparator;

import java.util.Arrays;
import java.util.Set;

public class TfIdfComparator extends BaseSimpleComparator {

    @Override
    public double compare(String[] text1, String[] text2) {
        Set<String> words = textsToSet(text1, text2);
        double[][] similarityVec = initSimilarityVector(words);
        int i = 0;

        for (String word : words) {
            similarityVec[0][i] = tfIdf(word, text1, text2);
            similarityVec[1][i] = tfIdf(word, text2, text1);
            i++;
        }

        return cosineSimilarity(similarityVec, words.size());
    }

    private double tfIdf(String word, String[] text1, String[] text2) {
        double text1LengthDouble = ((double) text1.length);
        double wordOccurrenceText1 = ((double) Arrays.stream(text1).filter(w -> w.equals(word)).count());
        double nQ = 1.0 + (Arrays.asList(text2).contains(word) ? 1.0 : 0.0);//TODO: poprawic w drugim tesz

        double tf = wordOccurrenceText1 / text1LengthDouble;

        double idf = Math.log10(TEXTS_COUNT / nQ);

        return tf * idf;
    }

}
