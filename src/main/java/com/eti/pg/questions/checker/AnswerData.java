package com.eti.pg.questions.checker;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerData {

    private String question;
    private String sampleAnswer;
    private String correctUserAnswer;
    private String wrongUserAnswer;
    private String partiallyCorrectUserAnswer;
    private Double expertScorePCUA;

}
