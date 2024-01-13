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
        for (AnswerData answer : data.getAnswers()) {
            double score = comparator.compare(answer.getSampleAnswer(), answer.getCorrectUserAnswer());
            System.out.println("[correct answer] expected: 100%; actual: " + score * 100.0 + "%;");
        }
    }

    @Test
    public void compare_incorrect() {
        for (AnswerData answer : data.getAnswers()) {
            double score = comparator.compare(answer.getSampleAnswer(), answer.getWrongUserAnswer());
            System.out.println("[incorrect answer] expected: 0%; actual: " + score * 100.0 + "%;");
        }
    }

    @Test
    public void compare_partiallyCorrect() {
        for (AnswerData answer : data.getAnswers()) {
            double score = comparator.compare(answer.getSampleAnswer(), answer.getCorrectUserAnswer());
            System.out.println("[partially correct answer] expected: " + answer.getExpertScorePCUA() + "%; actual: " + score * 100.0 + "%;");
        }
    }

}
