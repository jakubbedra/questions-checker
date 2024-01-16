package com.eti.pg.questions.checker.comparator.basecase;

import com.eti.pg.questions.checker.AnswerData;
import com.eti.pg.questions.checker.AnswerDataParser;
import com.eti.pg.questions.checker.AnswersData;
import com.eti.pg.questions.checker.comparator.OpenAIComparator;
import com.eti.pg.questions.checker.comparator.rest.dto.ChatGptGradingResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class BaseCaseOpenAiTest {

    protected OpenAIComparator comparator;
    protected AnswersData data;
    protected String model;

    public BaseCaseOpenAiTest(String path, String model, String language) {
        this.comparator = new OpenAIComparator(language);
        this.model = model;
        AnswerDataParser parser = new AnswerDataParser();
        data = parser.parse(path);
    }

    @Test
    public void compare_correct() {
        double minError = Integer.MAX_VALUE;
        double maxError = Integer.MIN_VALUE;
        double errorSum = 0.0;
        for (AnswerData answer : data.getAnswers()) {
            ChatGptGradingResponse response = comparator.compare(answer.getCorrectUserAnswer(), answer.getSampleAnswer(), answer.getQuestion(), model);
            double score = response.getScore();
            double error = Math.abs(100.0 - score);
            System.out.println("[correct answer] expected: 100%; actual: " + score + "%;");
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
            ChatGptGradingResponse response = comparator.compare(answer.getWrongUserAnswer(), answer.getSampleAnswer(), answer.getQuestion(), model);
            double score  = response.getScore();
            double error = Math.abs(0.0 - score);
            System.out.println("[correct answer] expected: 0%; actual: " + response.getScore() + "%;");
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
            ChatGptGradingResponse response = comparator.compare(answer.getPartiallyCorrectUserAnswer(), answer.getSampleAnswer(), answer.getQuestion(), model);
            double score  = response.getScore();
            double error = Math.abs(answer.getExpertScorePCUA() - score);
            System.out.println("[correct answer] expected: " + answer.getExpertScorePCUA() + "%; actual: " + response.getScore() + "%;");
            minError = Math.min(error, minError);
            maxError = Math.max(error, maxError);
            errorSum += error;
        }
        System.out.println("[partially correct answer - errors] min error: " + minError + "%; max error: " + maxError + "%; average error: " + errorSum/(double) data.getAnswers().size() + ";");
        System.out.println();
    }

}