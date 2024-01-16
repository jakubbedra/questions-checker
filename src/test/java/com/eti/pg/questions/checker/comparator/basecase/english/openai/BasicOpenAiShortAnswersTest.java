package com.eti.pg.questions.checker.comparator.basecase.english.openai;

import com.eti.pg.questions.checker.comparator.ComparatorTestConstants;
import com.eti.pg.questions.checker.comparator.basecase.BaseCaseOpenAiTest;

public class BasicOpenAiShortAnswersTest extends BaseCaseOpenAiTest {
    public BasicOpenAiShortAnswersTest() {
        super(ComparatorTestConstants.EN_SHORT_ANSWERS_PATH, "basic", "en");
    }
}