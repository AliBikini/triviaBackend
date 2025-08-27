package com.trivia.trivia.controllers;

import com.trivia.trivia.exceptions.OpenTriviaApiConnectionException;
import com.trivia.trivia.exceptions.OpenTriviaApiDataProcessingException;
import com.trivia.trivia.models.TriviaQuestion;
import com.trivia.trivia.models.TriviaQuiz;
import com.trivia.trivia.models.dtos.TriviaQuizQuestionsDTO;
import com.trivia.trivia.models.dtos.TriviaQuizResultsDTO;
import com.trivia.trivia.repos.trivaQuizRepo.TriviaQuizRepoMemory;
import com.trivia.trivia.services.triviaQuestionService.ITriviaQuestionService;
import com.trivia.trivia.services.triviaQuizService.TriviaQuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
class TriviaControllerTest {
    @Autowired
    @InjectMocks
    private TriviaController controller;

    @Mock
    private ITriviaQuestionService questionService;

    @Mock
    private TriviaQuizService quizService;

    private TriviaQuiz quiz;
    private List<TriviaQuestion> questionList;

    @BeforeEach
    void setupTests() throws OpenTriviaApiConnectionException, OpenTriviaApiDataProcessingException {
        questionList = new ArrayList<>();

        questionList.add(new TriviaQuestion(0, "test", "test", "test", "test", "test", new String[]{"", ""}));
        questionList.add(new TriviaQuestion(1, "test", "test", "test", "test", "test", new String[]{"", ""}));
        questionList.add(new TriviaQuestion(2, "test", "test", "test", "test", "test", new String[]{"", ""}));
        questionList.add(new TriviaQuestion(3, "test", "test", "test", "test", "test", new String[]{"", ""}));

        quiz = new TriviaQuiz("test", questionList);
    }

    @Test
    void getQuestions_should_returnOKStatusAndTriviaQuizQuestionsDTO() throws OpenTriviaApiConnectionException, OpenTriviaApiDataProcessingException {
        when(questionService.getRandomTriviaQuestions()).thenReturn(questionList);
        when(quizService.create(anyList())).thenReturn(Optional.of(quiz));

        ResponseEntity response = controller.getQuestions();

        assertSame(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(TriviaQuizQuestionsDTO.class, response.getBody());
    }

    @Test
    void checkAnswers_should_returnOKStatusAndTriviaQuizResultsDTO_when_validQuizAndAnswers() {
        when(quizService.get(quiz.getId())).thenReturn(Optional.of(quiz));

        String[] answers = new String[questionList.size()];

        for (int i = 0; i < answers.length; i++) {
            answers[i] = "testAnswer";
        }

        ResponseEntity response = controller.checkAnswers(quiz.getId(), answers);
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(TriviaQuizResultsDTO.class, response.getBody());
    }

    @Test
    void checkAnswers_should_returnCONFLICTStatusAndError_when_nonExistentQuiz() {
        when(quizService.get(anyString())).thenReturn(Optional.empty());

        String[] answers = new String[questionList.size()];

        for (int i = 0; i < answers.length; i++) {
            answers[i] = "testAnswer";
        }

        ResponseEntity response = controller.checkAnswers(quiz.getId(), answers);
        assertSame(HttpStatus.CONFLICT, response.getStatusCode());
        assertInstanceOf(String.class, response.getBody());

        String responseString = (String)response.getBody();
        assertTrue(responseString.contains("error: Could not find quiz"));
    }

    @Test
    void checkAnswers_should_returnCONFLICTStatusAndError_when_noAnswersSubmitted() {
        when(quizService.get(quiz.getId())).thenReturn(Optional.of(quiz));

        String[] answers = new String[0];

        ResponseEntity response = controller.checkAnswers(quiz.getId(), answers);
        assertSame(HttpStatus.CONFLICT, response.getStatusCode());
        assertInstanceOf(String.class, response.getBody());

        String responseString = (String)response.getBody();
        assertTrue(responseString.contains("error: No answers submitted"));
    }

    @Test
    void checkAnswers_should_returnCONFLICTStatusAndError_when_numberOfAnswersMismatch() {
        when(quizService.get(quiz.getId())).thenReturn(Optional.of(quiz));

        String[] answers = new String[questionList.size() - 1];

        for (int i = 0; i < answers.length; i++) {
            answers[i] = "testAnswer";
        }

        ResponseEntity response = controller.checkAnswers(quiz.getId(), answers);
        assertSame(HttpStatus.CONFLICT, response.getStatusCode());
        assertInstanceOf(String.class, response.getBody());

        String responseString = (String)response.getBody();
        assertTrue(responseString.contains("error: Number of answers"));
    }
}