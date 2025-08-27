package com.trivia.trivia.models;

public class TriviaQuestion {
    private int index;
    private String type;
    private String difficulty;
    private String category;
    private String question;
    private String correctAnswer;
    private String[] incorrectAnswers;

    public TriviaQuestion(int index, String type, String difficulty, String category, String question, String correctAnswer, String[] incorrectAnswers) {
        this.index = index;
        this.type = type;
        this.difficulty = difficulty;
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
