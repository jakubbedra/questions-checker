package com.eti.pg.questions.checker;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswersData {

    private List<AnswerData> answers;

}

