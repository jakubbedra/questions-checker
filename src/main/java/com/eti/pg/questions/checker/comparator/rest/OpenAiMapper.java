package com.eti.pg.questions.checker.comparator.rest;

import com.eti.pg.questions.checker.comparator.rest.dto.ChatGPTChoice;
import com.eti.pg.questions.checker.comparator.rest.dto.ChatGPTGetResponse;
import com.eti.pg.questions.checker.comparator.rest.dto.ChatGptGradingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class OpenAiMapper {

    public static final String PL_SYSTEM_PROMPT = "Pomóż mi sprawdzić moją wiedzę.\n" +
            "Na wejściu otrzymujesz zmienne [userAnswer], [sampleAnswer] oraz [question].\n" +
            "Porównaj treść odpowiedzi [userAnswer] z treścią przykładowej odpowiedzi [sampleAnswer], która odpowiada na pytanie [question].\n" +
            "Twoją odpowiedzią powinna być ocena od 0 do 100 w zależności od poprawności mojej odpowiedzi oraz komentarz wypisujący, czego nie ma w [userAnswer] a jest [sampleAnswer].\n" +
            "Kieruj się tym, jeśli [sampleAnswer] jest sensowna i odpowiada poprawnie na [question]. W innym wypadku oceń to na podstawie własnej wiedzy.\n" +
            "Skala ocen powinna być następująca:\n" +
            "0: Brak odpowiedzi, brak zrozumienia pytania, odpowiedź całkowicie nieadekwatna. Przepisanie treści pytania zamiast odpowiedzi na nie.\n" +
            "1-10: Minimalna odpowiedź.\n" +
            "11-20: Bardzo ograniczona odpowiedź, słabe zrozumienie pytania.\n" +
            "21-30: Prosta odpowiedź, podstawowe zrozumienie pytania.\n" +
            "31-40: Ograniczona odpowiedź, częściowe zrozumienie pytania.\n" +
            "41-50: Średnia odpowiedź, średnie zrozumienie pytania.\n" +
            "51-60: Dość dobra odpowiedź, dobre zrozumienie pytania.\n" +
            "61-70: Dobra odpowiedź, solidne zrozumienie pytania.\n" +
            "71-80: Bardzo dobra odpowiedź, bardzo dobre zrozumienie pytania.\n" +
            "81-90: Wspaniała odpowiedź, doskonałe zrozumienie pytania.\n" +
            "91-100: Wyjątkowa odpowiedź, perfekcyjne zrozumienie pytania.\n" +
            "Pomnóż uzyskany wynik przez poniższe wartości w zależności jakie błędy popełniłem w mojej odpowiedzi.\n" +
            "0.8: Brak odpowiedzi na małą część pytania.\n" +
            "0.6: Drobnym błędem w odpowiedzi, ale ogólnie zrozumiała.\n" +
            "0.4: Odpowiedź niekompletna, brakujące kluczowe elementy.\n" +
            "0.2: Odpowiedź częściowo poprawna, ale z istotnymi brakami.\n" +
            "0.0: Błąd krytyczny, kompletne niezrozumienie pytania.\n" +
            "Ważne jest to żebyś w swojej odpowiedzi nie używał zwrotów [userAnswer], [sampleAnswer] oraz [question]!\n" +
            "Zamiast [userAnswer], [sampleAnswer] oraz [question] używaj zwrotów: Twoja odpowiedź, przykładowa odpowiedź oraz pytanie.\n" +
            "Wypisz odpowiedź w formacie JSON: {\"score\": x, \"comment\": \"...\"}. Jeżeli będzie brakować  [userAnswer], [sampleAnswer] lub [question] twoją odpowiedzią ma być JSON: {\"score\": -1, \"comment\": \"Niewłaściwy format danych.\"}\n";

    public static final String EN_SYSTEM_PROMPT = "Help me with checking my knowledge.\n" +
            "As input you receive the following variables: [userAnswer], [sampleAnswer] and [question].\n" +
            "Compare the content of [userAnswer] with the content of [sampleAnswer], which answers [question].\n" +
            "Your response should contain a grade from 0 to 100 depending on correctness of [userAnswer] and a comment, that points out things that are not in [userAnswer], but are present in [sampleAnswer].\n" +
            "The grade scale should be as follows:\n" +
            "0: No answer. Question not understood by the user. Completely inadequate answer. Copying the [question] content and pasting it into [userAnswer].\n" +
            "1-10: Extremely minimal answer\n" +
            "11-20: Very limited answer, weak understanding of the question.\n" +
            "21-30: Simple answer, basic understanding of the question\n" +
            "31-40: Limited answer, partial understanding of the question.\n" +
            "41-50: Average answer, average understanding of the question.\n" +
            "51-60: Pretty good answer.\n" +
            "61-70: Good answer, solid understanding of the question.\n" +
            "71-80: Very good answer, very good understanding of the question.\n" +
            "81-90: Great answer, perfect understanding of the question.\n" +
            "91-100: Perfect answer. User perfectly understood the question.\n" +
            "Multiply the received score with below values depending on user mistakes:\n" +
            "0.8: Lack of answer for a small part of the question.\n" +
            "0.6: Simple mistake in answer, but overall understandable.\n" +
            "0.4: Incomplete answer, missing crucial elements.\n" +
            "0.2: Answer with significant mistakes.\n" +
            "0.0: Critical mistake, answer not related to the question.\n" +
            "It is important for you to not use any of the [userAnswer], [sampleAnswer] or [question] contents in your response!\n" +
            "Instead of [userAnswer], [sampleAnswer] and [question], use \"your answer\", \"sample answer\" and \"question\"/\n" +
            "Write the output in JSON format: {\"score\": x, \"comment\": \"...\"} If any of [userAnswer], [sampleAnswer] or [question] will be missing, your response should be: {\"score\":-1, \"comment\":\"Invalid data format.\"} ";

    private static ObjectMapper objectMapper = new ObjectMapper();

    public ChatGptGradingResponse chatGptAnswerToDto(ChatGPTGetResponse chatGPTGetResponse) {
        String comment = "";
        Optional<ChatGPTChoice> gptChoice = chatGPTGetResponse.getChoices().stream().findFirst();

        if (gptChoice.isPresent()) {
            comment = gptChoice.get().getMessage().getContent();
        }
        try {
            return objectMapper.readValue(comment, ChatGptGradingResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String userAnswerAndQuestionToPrompt(String userAnswer, String sampleAnswer, String question) {
        return "Oto wartości zmiennych:\n"
                + String.format("\n[userAnswer]: %s", userAnswer)
                + String.format("\n[sampleAnswer]: %s", sampleAnswer)
                + String.format("\n[question]: %s", question);
    }

}
