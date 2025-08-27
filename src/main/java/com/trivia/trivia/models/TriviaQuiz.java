package com.trivia.trivia.models;

import java.util.List;

public class TriviaQuiz {
    private String id;
    private List<TriviaQuestion> questions;

    public TriviaQuiz(String id, List<TriviaQuestion> questions) {
        this.id = id;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public List<TriviaQuestion> getQuestions() {
        return questions;
    }
}
