package com.eti.pg.questions.checker.comparator.rest.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatGPTChoice {
    private int index;
    private ChatGPTMessage message;
}