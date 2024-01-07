package com.eti.pg.questions.checker.comparator.rest.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatGPTGetRequest {
    private String model;
    private List<ChatGPTMessage> messages;
    private int n;
    private double temperature;
    private int max_tokens;
}
