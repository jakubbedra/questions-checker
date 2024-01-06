package com.eti.pg.questions.checker.comparator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseSimpleComparator implements BaseAnswerComparator {

    protected static final double TEXTS_COUNT = 2.0;

    public abstract double compare(String[] text1, String[] text2);

    @Override
    public double compare(String text1, String text2) {
        // split by spaces
        return compare(text1.split(" "), text2.split(" "));
    }

    protected double cosineSimilarity(double[][] vec, int n) {
        double dotProduct = 0.0;
        double lengthA = 0.0;
        double lengthB = 0.0;

        for (int i = 0; i < n; i++) {
            dotProduct += vec[0][i] * vec[1][i];
            lengthA += vec[0][i] * vec[0][i];
            lengthB += vec[1][i] * vec[1][i];
        }

        lengthA = Math.sqrt(lengthA);
        lengthB = Math.sqrt(lengthB);

        return dotProduct / (lengthA * lengthB);
    }

    protected Set<String> textsToSet(String[] text1, String[] text2) {
        Set<String> allWords = new HashSet<>(Arrays.asList(text1));
        allWords.addAll(Arrays.asList(text2));
        return allWords;
    }

    protected double[][] initSimilarityVector(Set<String> words) {
        double[][] similarityVec = new double[2][words.size()];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < words.size(); j++) {
                similarityVec[i][j] = 0.0;
            }
        }
        return similarityVec;
    }

}

