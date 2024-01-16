package com.eti.pg.questions.checker.comparator.basecase.english.openai;

import com.eti.pg.questions.checker.comparator.ComparatorTestConstants;
import com.eti.pg.questions.checker.comparator.basecase.BaseCaseOpenAiTest;

public class BasicOpenAiLongAnswersTest extends BaseCaseOpenAiTest {
    public BasicOpenAiLongAnswersTest() {
        super(ComparatorTestConstants.EN_LONG_ANSWERS_PATH, "basic", "en");
    }
}