package com.eti.pg.questions.checker.comparator.cornercase;

import com.eti.pg.questions.checker.AnswerData;
import com.eti.pg.questions.checker.AnswerDataParser;
import com.eti.pg.questions.checker.AnswersData;
import com.eti.pg.questions.checker.comparator.ComparatorTestConstants;
import com.eti.pg.questions.checker.comparator.OpenAIComparator;
import com.eti.pg.questions.checker.comparator.rest.dto.ChatGptGradingResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class BaseCornerCaseTest {

    protected OpenAIComparator comparator;
    protected String model;

    protected AnswersData emptyAnswersData;
    protected AnswersData inadequateAnswersData;
    protected AnswersData questionCopiesData;
    protected AnswersData sentenceNegationsData;

    public BaseCornerCaseTest(String model, String language) {
        this.comparator = new OpenAIComparator(language);
        this.model = model;
        AnswerDataParser parser = new AnswerDataParser();
        emptyAnswersData = parser.parse(language.equals("pl") ? ComparatorTestConstants.PL_EMPTY_ANSWERS_PATH : ComparatorTestConstants.EN_EMPTY_ANSWERS_PATH);
        inadequateAnswersData = parser.parse(language.equals("pl") ? ComparatorTestConstants.PL_INADEQUATE_ANSWERS_PATH : ComparatorTestConstants.EN_INADEQUATE_ANSWERS_PATH);
        questionCopiesData = parser.parse(language.equals("pl") ? ComparatorTestConstants.PL_QUESTION_COPIES_PATH : ComparatorTestConstants.EN_QUESTION_COPIES_PATH);
        sentenceNegationsData = parser.parse(language.equals("pl") ? ComparatorTestConstants.PL_SENTENCE_NEGATIONS_PATH : ComparatorTestConstants.EN_SENTENCE_NEGATIONS_PATH);
    }

    @Test
    public void compare_emptyAnswers() {
        double minError = Integer.MAX_VALUE;
        double maxError = Integer.MIN_VALUE;
        double errorSum = 0.0;
        for (AnswerData answer : emptyAnswersData.getAnswers()) {
            ChatGptGradingResponse response = comparator.compare(answer.getWrongUserAnswer(), answer.getSampleAnswer(), answer.getQuestion(), model);
            double score = response.getScore();
            double error = Math.abs(0.0 - score);
            System.out.println("[correct answer] expected: 0%; actual: " + score + "%;");
            minError = Math.min(error, minError);
            maxError = Math.max(error, maxError);
            errorSum += error;
        }
        System.out.println("[correct answer - errors] min error: " + minError + "%; max error: " + maxError + "%; average error: " + errorSum/(double) emptyAnswersData.getAnswers().size() + ";");
        System.out.println();
    }

    @Test
    public void compare_inadequateAnswers() {
        double minError = Integer.MAX_VALUE;
        double maxError = Integer.MIN_VALUE;
        double errorSum = 0.0;
        for (AnswerData answer : inadequateAnswersData.getAnswers()) {
            ChatGptGradingResponse response = comparator.compare(answer.getWrongUserAnswer(), answer.getSampleAnswer(), answer.getQuestion(), model);
            double score = response.getScore();
            double error = Math.abs(0.0 - score);
            System.out.println("[correct answer] expected: 0%; actual: " + score + "%;");
            minError = Math.min(error, minError);
            maxError = Math.max(error, maxError);
            errorSum += error;
        }
        System.out.println("[correct answer - errors] min error: " + minError + "%; max error: " + maxError + "%; average error: " + errorSum/(double) inadequateAnswersData.getAnswers().size() + ";");
        System.out.println();
    }

    @Test
    public void compare_questionCopies() {
        double minError = Integer.MAX_VALUE;
        double maxError = Integer.MIN_VALUE;
        double errorSum = 0.0;
        for (AnswerData answer : questionCopiesData.getAnswers()) {
            ChatGptGradingResponse response = comparator.compare(answer.getQuestion(), answer.getSampleAnswer(), answer.getQuestion(), model);
            double score = response.getScore();
            double error = Math.abs(0.0 - score);
            System.out.println("[correct answer] expected: 0%; actual: " + score + "%;");
            minError = Math.min(error, minError);
            maxError = Math.max(error, maxError);
            errorSum += error;
        }
        System.out.println("[correct answer - errors] min error: " + minError + "%; max error: " + maxError + "%; average error: " + errorSum/(double) questionCopiesData.getAnswers().size() + ";");
        System.out.println();
    }

    @Test
    public void compare_sentenceNegations() {
        double minError = Integer.MAX_VALUE;
        double maxError = Integer.MIN_VALUE;
        double errorSum = 0.0;
        for (AnswerData answer : sentenceNegationsData.getAnswers()) {
            ChatGptGradingResponse response = comparator.compare(answer.getQuestion(), answer.getSampleAnswer(), answer.getQuestion(), model);
            double score = response.getScore();
            double error = Math.abs(0.0 - score);
            System.out.println("[correct answer] expected: 0%; actual: " + score + "%;");
            minError = Math.min(error, minError);
            maxError = Math.max(error, maxError);
            errorSum += error;
        }
        System.out.println("[correct answer - errors] min error: " + minError + "%; max error: " + maxError + "%; average error: " + errorSum/(double) sentenceNegationsData.getAnswers().size() + ";");
        System.out.println();
    }


}
