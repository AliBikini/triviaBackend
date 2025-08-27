package com.trivia.trivia.exceptions;

public class OpenTriviaApiDataProcessingException extends Exception {
    public OpenTriviaApiDataProcessingException() {
        super("Could not process received Open Trivia Api data");
    }
}
