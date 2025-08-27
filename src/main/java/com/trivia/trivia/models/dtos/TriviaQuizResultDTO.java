package com.trivia.trivia.models.dtos;

public class TriviaQuizResultDTO {
    private int index;
    private boolean isCorrect;

    public TriviaQuizResultDTO(int index, boolean isCorrect) {
        this.index = index;
        this.isCorrect = isCorrect;
    }

    public int getIndex() {
        return index;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }
}
