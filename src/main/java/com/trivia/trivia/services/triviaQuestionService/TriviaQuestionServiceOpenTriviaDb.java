package com.trivia.trivia.services.triviaQuestionService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.trivia.trivia.exceptions.OpenTriviaApiConnectionException;
import com.trivia.trivia.exceptions.OpenTriviaApiDataProcessingException;
import com.trivia.trivia.models.TriviaQuestion;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TriviaQuestionServiceOpenTriviaDb implements ITriviaQuestionService {
    private String url;
    private OpenTriviaDbWebClient webClient;

    public TriviaQuestionServiceOpenTriviaDb() {
        url = "https://opentdb.com/api.php?amount=10";
        webClient = new OpenTriviaDbWebClient();
    }

    @Async
    public List<TriviaQuestion> getRandomTriviaQuestions() throws OpenTriviaApiConnectionException, OpenTriviaApiDataProcessingException {
        String response = "";

        try
        {
            response = webClient.getRandomQuestions(url);
        }
        catch (Exception e)
        {
            throw new OpenTriviaApiConnectionException();
        }

        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule("OpenTriviaDbQuestionsDeserializer");
            module.addDeserializer(TriviaQuestion[].class, new OpenTriviaDbTriviaQuestionsDeserializer());
            objectMapper.registerModule(module);

            TriviaQuestion[] questions = objectMapper.readValue(response, TriviaQuestion[].class);
            return Arrays.stream(questions).toList();
        }
        catch (Exception e)
        {
            throw new OpenTriviaApiDataProcessingException();
        }
    }
}
