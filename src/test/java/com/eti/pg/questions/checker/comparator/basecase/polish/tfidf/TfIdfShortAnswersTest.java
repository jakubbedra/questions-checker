package com.eti.pg.questions.checker.comparator.basecase.polish.tfidf;

import com.eti.pg.questions.checker.comparator.TfIdfComparator;
import com.eti.pg.questions.checker.comparator.basecase.polish.TestBaseShortAnswers;

public class TfIdfShortAnswersTest extends TestBaseShortAnswers {

    public TfIdfShortAnswersTest() {
        super(new TfIdfComparator());
    }

}