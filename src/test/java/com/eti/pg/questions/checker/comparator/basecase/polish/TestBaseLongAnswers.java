package com.eti.pg.questions.checker.comparator.basecase.polish;

import com.eti.pg.questions.checker.comparator.BaseAnswerComparator;
import com.eti.pg.questions.checker.comparator.ComparatorTestConstants;
import com.eti.pg.questions.checker.comparator.basecase.BaseCaseTest;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TestBaseLongAnswers extends BaseCaseTest {

    public TestBaseLongAnswers(BaseAnswerComparator comparator) {
        super(ComparatorTestConstants.LONG_ANSWERS_PATH, comparator);
    }

}
