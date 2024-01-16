package com.eti.pg.questions.checker.comparator.basecase;

import com.eti.pg.questions.checker.AnswerData;
import com.eti.pg.questions.checker.AnswerDataParser;
import com.eti.pg.questions.checker.AnswersData;
import com.eti.pg.questions.checker.comparator.BaseAnswerComparator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class BaseCaseTest {

    protected BaseAnswerComparator comparator;
    protected AnswersData data;

    public BaseCaseTest(String path, BaseAnswerComparator comparator) {
        this.comparator = comparator;
        AnswerDataParser parser = new AnswerDataParser();
        data = parser.parse(path);
    }

    @Test
    public void compare_correct() {
        double minError = Integer.MAX_VALUE;
        double maxError = Integer.MIN_VALUE;
        double errorSum = 0.0;
        for (AnswerData answer : data.getAnswers()) {
            double score = comparator.compare(answer.getSampleAnswer(), answer.getCorrectUserAnswer());
            score *= 100.0;
            double error = Math.abs(100.0 - score);
            System.out.println("[correct answer] expected: 100%; actual: " + score + "%; error: " + error + ";");
            minError = Math.min(error, minError);
            maxError = Math.max(error, maxError);
            errorSum += error;
        }
        System.out.println("[correct answer - errors] min error: " + minError + "%; max error: " + maxError + "%; average error: " + errorSum/(double) data.getAnswers().size() + ";");
        System.out.println();
    }

    @Test
    public void compare_incorrect() {
        double minError = Integer.MAX_VALUE;
        double maxError = Integer.MIN_VALUE;
        double errorSum = 0.0;
        for (AnswerData answer : data.getAnswers()) {
            double score = comparator.compare(answer.getSampleAnswer(), answer.getWrongUserAnswer());
            score *= 100.0;
            double error = Math.abs(0.0 - score);
            System.out.println("[incorrect answer] expected: 0%; actual: " + score + "%; error: " + error + ";");
            minError = Math.min(error, minError);
            maxError = Math.max(error, maxError);
            errorSum += error;
        }
        System.out.println("[incorrect answer - errors] min error: " + minError + "%; max error: " + maxError + "%; average error: " + errorSum/(double) data.getAnswers().size() + ";");
        System.out.println();
    }

    @Test
    public void compare_partiallyCorrect() {
        double minError = Integer.MAX_VALUE;
        double maxError = Integer.MIN_VALUE;
        double errorSum = 0.0;
        for (AnswerData answer : data.getAnswers()) {
            double score = comparator.compare(answer.getSampleAnswer(), answer.getCorrectUserAnswer());
            score *= 100.0;
            double error = Math.abs(answer.getExpertScorePCUA() - score);
            System.out.println("[partially correct answer] expected: " + answer.getExpertScorePCUA() + "%; actual: " + score + "%; error: " + error + ";");
            minError = Math.min(error, minError);
            maxError = Math.max(error, maxError);
            errorSum += error;
        }
        System.out.println("[partially correct answer - errors] min error: " + minError + "%; max error: " + maxError + "%; average error: " + errorSum/(double) data.getAnswers().size() + ";");
        System.out.println();
    }

}
