package com.trivia.trivia.models.dtos;

public class TriviaQuizQuestionDTO {
    private int index;
    private String type;
    private String difficulty;
    private String category;
    private String question;
    private String[] possibleAnswers;

    public TriviaQuizQuestionDTO(int index, String type, String difficulty, String category, String question, String[] possibleAnswers) {
        this.index = index;
        this.type = type;
        this.difficulty = difficulty;
        this.category = category;
        this.question = question;
        this.possibleAnswers = possibleAnswers;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(String[] possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
