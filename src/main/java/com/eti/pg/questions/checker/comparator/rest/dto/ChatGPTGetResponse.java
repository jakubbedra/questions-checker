package com.eti.pg.questions.checker.comparator.rest.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatGPTGetResponse {
    private List<ChatGPTChoice> choices;
}