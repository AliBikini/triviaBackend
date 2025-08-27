package com.trivia.trivia.repos.trivaQuizRepo;

import com.trivia.trivia.models.TriviaQuestion;
import com.trivia.trivia.models.TriviaQuiz;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class TriviaQuizRepoMemory implements  ITriviaQuizRepo {
    private List<TriviaQuiz> quizzes;

    public TriviaQuizRepoMemory() {
        quizzes = new ArrayList<TriviaQuiz>();
    }

    public Optional<TriviaQuiz> create(List<TriviaQuestion> triviaQuestions) {

        if (triviaQuestions.isEmpty())
        {
            return Optional.empty();
        }

        TriviaQuiz quiz = new TriviaQuiz(GenerateRandomId(), triviaQuestions);
        quizzes.add(quiz);
        return Optional.of(quiz);
    }

    private String GenerateRandomId() {
        Random rand = new Random();
        int rnd = rand.nextInt(500);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String dateFormatted = formatter.format(dateTime);
        return dateFormatted + rnd;
    }

    public Optional<TriviaQuiz> get(String id) {
        for (int i = 0; i < quizzes.size(); i++)
        {
            if (quizzes.get(i).getId().equals(id))
            {
                return Optional.of(quizzes.get(i));
            }
        }

        return Optional.empty();
    }

    public List<TriviaQuiz> getAll() {
        return quizzes;
    }
}
