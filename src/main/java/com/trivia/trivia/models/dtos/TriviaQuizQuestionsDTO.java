package com.trivia.trivia.models.dtos;

import com.trivia.trivia.models.TriviaQuestion;
import com.trivia.trivia.models.TriviaQuiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TriviaQuizQuestionsDTO {
    private String idQuiz;
    private List<TriviaQuizQuestionDTO> questions;

    public TriviaQuizQuestionsDTO(TriviaQuiz quiz) {
        this.idQuiz = quiz.getId();
        questions = new ArrayList<TriviaQuizQuestionDTO>();
        Random rand = new Random();

        for (TriviaQuestion question : quiz.getQuestions()) {
            List<String> possibleAnswers = new ArrayList<String>(Arrays.asList(question.getIncorrectAnswers()));
            int rnd = rand.nextInt(0, possibleAnswers.size());
            possibleAnswers.add(rnd, question.getCorrectAnswer());

            TriviaQuizQuestionDTO questionDto = new TriviaQuizQuestionDTO(question.getIndex(), question.getType(), question.getDifficulty(), question.getCategory(), question.getQuestion(), possibleAnswers.toArray(new String[0]));
            questions.add(questionDto);
        }
    }

    public String getIdQuiz() {
        return idQuiz;
    }

    public List<TriviaQuizQuestionDTO> getQuestions() {
        return questions;
    }
}
