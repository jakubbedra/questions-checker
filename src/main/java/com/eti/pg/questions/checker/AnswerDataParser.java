package com.eti.pg.questions.checker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AnswerDataParser {

    private ObjectMapper objectMapper;

    public AnswerDataParser() {
        objectMapper = new ObjectMapper();
    }

    public AnswersData parse(String path) {
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String fileContent = new String(fileBytes);
        try {
            return objectMapper.readValue(fileContent, AnswersData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
