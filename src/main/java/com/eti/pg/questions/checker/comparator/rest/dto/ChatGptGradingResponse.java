package com.eti.pg.questions.checker.comparator.rest.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatGptGradingResponse {
    private Double score;
    private String comment;
}
