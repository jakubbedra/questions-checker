package com.eti.pg.questions.checker.comparator.rest;


import com.eti.pg.questions.checker.comparator.rest.dto.ChatGPTGetRequest;
import com.eti.pg.questions.checker.comparator.rest.dto.ChatGPTGetResponse;
import com.eti.pg.questions.checker.comparator.rest.dto.ChatGPTMessage;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class OpenAiService {

    private RestTemplate restTemplate;

    public OpenAiService() {

        restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + OpenAiConstants.SECRET_KEY);
            return execution.execute(request, body);
        });

        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

    public ChatGPTGetResponse sendAnswer(String prompt, String type, String language) {
        ChatGPTGetRequest request = ChatGPTGetRequest
                .builder()
                .n(OpenAiConstants.MAX_COMPLETIONS)
                .max_tokens(OpenAiConstants.MAX_TOKENS)
                .temperature(OpenAiConstants.TEMPERATURE)
                .model(type.equals("basic") ? OpenAiConstants.BASIC_MODEL : OpenAiConstants.FINE_TUNED_MODEL)
                .messages(List.of(
                        ChatGPTMessage.builder().role("system").content(language.equals("pl") ? OpenAiMapper.PL_SYSTEM_PROMPT : OpenAiMapper.EN_SYSTEM_PROMPT).build(),
                        ChatGPTMessage.builder().role("user").content(prompt).build()
                ))
                .build();
        try {
            ChatGPTGetResponse response = restTemplate.postForObject(OpenAiConstants.API_URL, request, ChatGPTGetResponse.class);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
