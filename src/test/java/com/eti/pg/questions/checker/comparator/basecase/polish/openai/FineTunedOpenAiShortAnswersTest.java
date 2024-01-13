package com.eti.pg.questions.checker.comparator.basecase.polish.openai;

import com.eti.pg.questions.checker.comparator.ComparatorTestConstants;
import com.eti.pg.questions.checker.comparator.basecase.BaseCaseOpenAiTest;

public class FineTunedOpenAiShortAnswersTest extends BaseCaseOpenAiTest {
    public FineTunedOpenAiShortAnswersTest() {
        super(ComparatorTestConstants.SHORT_ANSWERS_PATH, "fine-tuned");
    }
}