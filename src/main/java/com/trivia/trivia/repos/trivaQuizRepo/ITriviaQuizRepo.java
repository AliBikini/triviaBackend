package com.trivia.trivia.repos.trivaQuizRepo;

import com.trivia.trivia.models.TriviaQuestion;
import com.trivia.trivia.models.TriviaQuiz;

import java.util.List;
import java.util.Optional;

public interface ITriviaQuizRepo {
    public Optional<TriviaQuiz> create(List<TriviaQuestion> triviaQuestions);
    public Optional<TriviaQuiz> get(String id);
    public List<TriviaQuiz> getAll();
}
