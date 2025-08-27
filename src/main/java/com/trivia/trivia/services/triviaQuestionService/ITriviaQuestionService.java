package com.trivia.trivia.services.triviaQuestionService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trivia.trivia.exceptions.OpenTriviaApiConnectionException;
import com.trivia.trivia.exceptions.OpenTriviaApiDataProcessingException;
import com.trivia.trivia.models.TriviaQuestion;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface ITriviaQuestionService
{
    @Async
    public List<TriviaQuestion> getRandomTriviaQuestions() throws OpenTriviaApiConnectionException, OpenTriviaApiDataProcessingException;
}
