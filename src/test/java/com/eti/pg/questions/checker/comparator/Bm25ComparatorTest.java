package com.eti.pg.questions.checker.comparator;

import org.junit.Test;

public class Bm25ComparatorTest {

    @Test
    public void compareTest_shortAnswer() {
        // arrange
        String[] text1 = {"pg", "to", "najbardziej", "prostudencka", "uczelnia", "w", "trojmiescie"};
        String[] text2 = {"pg", "to", "najbardziej", "nie", "prostudencka", "uczelnia", "w", "trojmiescie"};
//        String[] text2 = {"pg", "to", "prostudencka", "uczelnia", "znajdujaca", "sie", "w", "trojmiescie"};


        //String[] text2 = {"pg", "to", "nie", "najbardziej", "prostudencka", "uczelnia", "w", "trojmiescie"};
//        String[] text2 = {"pg", "to", "najbardziej", "prostudencka", "uczelnia", "w", "trojmiescie"};
        //String[] text2 = {"pg", "nie", "jest", "prostudencka", "uczelnia", "to", "fakt"};
//        String[] text2 = {"pg", "jest", "najbardziej", "skurwiala", "uczelnia", "w", "trojmiescie"};
        BaseSimpleComparator comparator = new Bm25Comparator();
//        BaseSimpleComparator comparator = new TfIdfComparator();

        // act
        double score = comparator.compare(text1, text2);

        // assert
        System.out.println(score);
    }

}
