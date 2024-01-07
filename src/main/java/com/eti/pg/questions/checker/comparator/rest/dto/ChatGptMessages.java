package com.eti.pg.questions.checker.comparator.rest.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatGptMessages {
    private List<ChatGPTMessage> messages;
}
