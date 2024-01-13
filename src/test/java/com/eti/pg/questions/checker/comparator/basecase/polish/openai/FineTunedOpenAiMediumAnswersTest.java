package com.eti.pg.questions.checker.comparator.basecase.polish.openai;

import com.eti.pg.questions.checker.comparator.ComparatorTestConstants;
import com.eti.pg.questions.checker.comparator.basecase.BaseCaseOpenAiTest;

public class FineTunedOpenAiMediumAnswersTest extends BaseCaseOpenAiTest {
    public FineTunedOpenAiMediumAnswersTest() {
        super(ComparatorTestConstants.MEDIUM_ANSWERS_PATH, "fine-tuned");
    }
}