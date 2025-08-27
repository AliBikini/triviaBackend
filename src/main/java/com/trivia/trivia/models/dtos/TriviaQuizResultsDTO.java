package com.trivia.trivia.models.dtos;

import com.trivia.trivia.models.TriviaQuestion;
import com.trivia.trivia.models.TriviaQuiz;

import java.util.ArrayList;
import java.util.List;

public class TriviaQuizResultsDTO {
    public List<TriviaQuizResultDTO> results;

    public TriviaQuizResultsDTO(TriviaQuiz quiz, String[] answers) {
        results = new ArrayList<TriviaQuizResultDTO>();

        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            TriviaQuestion question = quiz.getQuestions().get(i);
            boolean isCorrect = question.getCorrectAnswer().equals(answers[i]);
            results.add(new TriviaQuizResultDTO(question.getIndex(), isCorrect));
        }
    }

    public List<TriviaQuizResultDTO> getResults() {
        return results;
    }
}
