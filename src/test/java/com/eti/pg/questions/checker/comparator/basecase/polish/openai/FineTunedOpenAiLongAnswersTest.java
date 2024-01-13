package com.eti.pg.questions.checker.comparator.basecase.polish.openai;

import com.eti.pg.questions.checker.comparator.ComparatorTestConstants;
import com.eti.pg.questions.checker.comparator.basecase.BaseCaseOpenAiTest;

public class FineTunedOpenAiLongAnswersTest extends BaseCaseOpenAiTest {
    public FineTunedOpenAiLongAnswersTest() {
        super(ComparatorTestConstants.MEDIUM_ANSWERS_PATH, "fine-tuned");
    }
}