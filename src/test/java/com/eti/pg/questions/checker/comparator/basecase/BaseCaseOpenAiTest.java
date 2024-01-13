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

    public BaseCaseOpenAiTest(String path, String model) {
        this.comparator = new OpenAIComparator();
        this.model = model;
        AnswerDataParser parser = new AnswerDataParser();
        data = parser.parse(path);
    }

    @Test
    public void compare_correct() {
        for (AnswerData answer : data.getAnswers()) {
            ChatGptGradingResponse response = comparator.compare(answer.getCorrectUserAnswer(), answer.getSampleAnswer(), answer.getQuestion(), model);
            System.out.println("[correct answer] expected: 100%; actual: " + response.getScore() + "%;");
        }
    }

    @Test
    public void compare_incorrect() {
        for (AnswerData answer : data.getAnswers()) {
            ChatGptGradingResponse response = comparator.compare(answer.getWrongUserAnswer(), answer.getSampleAnswer(), answer.getQuestion(), model);
            System.out.println("[correct answer] expected: 0%; actual: " + response.getScore() + "%;");
        }
    }

    @Test
    public void compare_partiallyCorrect() {
        for (AnswerData answer : data.getAnswers()) {
            ChatGptGradingResponse response = comparator.compare(answer.getPartiallyCorrectUserAnswer(), answer.getSampleAnswer(), answer.getQuestion(), model);
            System.out.println("[correct answer] expected: " + answer.getExpertScorePCUA() + "%; actual: " + response.getScore() + "%;");
        }
    }

}