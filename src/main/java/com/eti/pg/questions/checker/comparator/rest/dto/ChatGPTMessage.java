package com.eti.pg.questions.checker.comparator.rest.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatGPTMessage {
    private String role;
    private String content;
}