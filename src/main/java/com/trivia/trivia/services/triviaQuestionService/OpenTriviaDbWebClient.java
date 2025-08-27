package com.trivia.trivia.services.triviaQuestionService;

import org.springframework.web.reactive.function.client.WebClient;

public class OpenTriviaDbWebClient {
    public String getRandomQuestions(String url) {
        WebClient client = WebClient.create(url);
        return client.get().retrieve().bodyToMono(String.class).block();
    }
}
