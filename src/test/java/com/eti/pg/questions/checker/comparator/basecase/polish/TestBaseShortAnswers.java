package com.eti.pg.questions.checker.comparator.basecase.polish;

import com.eti.pg.questions.checker.comparator.BaseAnswerComparator;
import com.eti.pg.questions.checker.comparator.ComparatorTestConstants;
import com.eti.pg.questions.checker.comparator.basecase.BaseCaseTest;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TestBaseShortAnswers extends BaseCaseTest {

    public TestBaseShortAnswers(BaseAnswerComparator comparator) {
        super(ComparatorTestConstants.PL_SHORT_ANSWERS_PATH, comparator);
    }

}