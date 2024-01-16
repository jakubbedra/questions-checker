package com.eti.pg.questions.checker.comparator.basecase.polish.word2vec;

import com.eti.pg.questions.checker.comparator.Word2vecComparator;
import com.eti.pg.questions.checker.comparator.basecase.polish.TestBaseShortAnswers;

public class Word2VecShortAnswersTest  extends TestBaseShortAnswers {

    public Word2VecShortAnswersTest() {
        super(new Word2vecComparator());
    }

}