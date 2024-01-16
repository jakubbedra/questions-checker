package com.eti.pg.questions.checker.comparator;

import com.eti.pg.questions.checker.comparator.rest.OpenAiMapper;
import com.eti.pg.questions.checker.comparator.rest.OpenAiService;
import com.eti.pg.questions.checker.comparator.rest.dto.ChatGPTGetResponse;
import com.eti.pg.questions.checker.comparator.rest.dto.ChatGptGradingResponse;

public class OpenAIComparator {

    private OpenAiMapper mapper;
    private OpenAiService service;
    private String language;

    public OpenAIComparator(String language) {
        this.language = language;
        mapper = new OpenAiMapper();
        service = new OpenAiService();
    }

    public ChatGptGradingResponse compare(String userAnswer, String sampleAnswer, String question, String model) {
        String prompt = mapper.userAnswerAndQuestionToPrompt(userAnswer, sampleAnswer, question);
        ChatGPTGetResponse response = service.sendAnswer(prompt, model, language);
        return mapper.chatGptAnswerToDto(response);
    }

    public ChatGptGradingResponse compareBasic(String userAnswer, String sampleAnswer, String question) {
        String prompt = mapper.userAnswerAndQuestionToPrompt(userAnswer, sampleAnswer, question);
        ChatGPTGetResponse response = service.sendAnswer(prompt, "basic", language);
        return mapper.chatGptAnswerToDto(response);
    }

    public ChatGptGradingResponse compareFineTuned(String userAnswer, String sampleAnswer, String question) {
        String prompt = mapper.userAnswerAndQuestionToPrompt(userAnswer, sampleAnswer, question);
        ChatGPTGetResponse response = service.sendAnswer(prompt, "fine-tuned", language);
        return mapper.chatGptAnswerToDto(response);
    }

}
