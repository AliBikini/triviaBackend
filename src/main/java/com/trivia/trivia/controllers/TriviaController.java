package com.trivia.trivia.controllers;

import com.trivia.trivia.exceptions.OpenTriviaApiConnectionException;
import com.trivia.trivia.exceptions.OpenTriviaApiDataProcessingException;
import com.trivia.trivia.models.TriviaQuestion;
import com.trivia.trivia.models.TriviaQuiz;
import com.trivia.trivia.models.dtos.TriviaQuizQuestionsDTO;
import com.trivia.trivia.models.dtos.TriviaQuizResultsDTO;
import com.trivia.trivia.services.triviaQuizService.TriviaQuizService;
import com.trivia.trivia.services.triviaQuestionService.ITriviaQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
public class TriviaController {
    private ITriviaQuestionService triviaQuestionService;
    private TriviaQuizService triviaQuizService;

    public TriviaController(ITriviaQuestionService triviaQuestionService, TriviaQuizService triviaQuizService) {
        this.triviaQuestionService = triviaQuestionService;
        this.triviaQuizService = triviaQuizService;
    }

    @GetMapping("/questions")
    @ResponseBody
    public ResponseEntity getQuestions() throws OpenTriviaApiConnectionException, OpenTriviaApiDataProcessingException {
        List<TriviaQuestion> questionsResult = triviaQuestionService.getRandomTriviaQuestions();
        Optional<TriviaQuiz> quiz = triviaQuizService.create(questionsResult);

        if (quiz.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("error: No questions found from Open Trivia Api");
        }

        return ResponseEntity.status(HttpStatus.OK).body(new TriviaQuizQuestionsDTO(quiz.get()));
    }

    @ExceptionHandler(OpenTriviaApiDataProcessingException.class)
    public ResponseEntity<String> jsonParseError() {
        return new ResponseEntity<String>("error: Error with processing data from Open Trivia Api", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OpenTriviaApiConnectionException.class)
    public ResponseEntity<String> openTriviaApiError() {
        return new ResponseEntity<String>("error: Error with connection to Open Trivia Api", HttpStatus.CONFLICT);
    }

    @PostMapping(path = "/checkanswers/{idQuiz}")
    @ResponseBody
    public ResponseEntity checkAnswers(@PathVariable String idQuiz, @RequestBody String[] answers) {
        Optional<TriviaQuiz> quiz = triviaQuizService.get(idQuiz);

        if (quiz.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("error: Could not find quiz with id: " + idQuiz);
        }

        if (answers.length == 0)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("error: No answers submitted");
        }

        if (answers.length != quiz.get().getQuestions().size())
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("error: Number of answers does not equal number of questions in quiz");
        }

        return ResponseEntity.status(HttpStatus.OK).body(new TriviaQuizResultsDTO(quiz.get(), answers));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<String>("error: Could not parse submitted data", HttpStatus.CONFLICT);
    }
}
