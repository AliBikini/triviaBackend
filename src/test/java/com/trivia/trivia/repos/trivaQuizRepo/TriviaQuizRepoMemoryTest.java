package com.trivia.trivia.repos.trivaQuizRepo;

import com.trivia.trivia.models.TriviaQuestion;
import com.trivia.trivia.models.TriviaQuiz;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class TriviaQuizRepoMemoryTest {
    @Autowired
    private TriviaQuizRepoMemory triviaRepo;

    @Test
    void create_should_createAndAddValidQuiz() {
        List<TriviaQuestion> questionList = new ArrayList<>();

        questionList.add(new TriviaQuestion(0, "test", "test", "test", "test", "test", new String[]{"", ""}));
        questionList.add(new TriviaQuestion(1, "test", "test", "test", "test", "test", new String[]{"", ""}));
        questionList.add(new TriviaQuestion(2, "test", "test", "test", "test", "test", new String[]{"", ""}));
        questionList.add(new TriviaQuestion(3, "test", "test", "test", "test", "test", new String[]{"", ""}));

        Optional<TriviaQuiz> quizResult = triviaRepo.create(questionList);
        List<TriviaQuiz> quizzesAll = triviaRepo.getAll();

        Assertions.assertThat(quizResult).isNotEmpty();
        Assertions.assertThat(quizzesAll).isNotEmpty();
    }

    @Test
    void create_should_rejectEmptyQuestionsListAndReturnEmpty() {
        List<TriviaQuestion> questionList = new ArrayList<>();
        Optional<TriviaQuiz> quizResult = triviaRepo.create(questionList);
        List<TriviaQuiz> quizzesAll = triviaRepo.getAll();

        Assertions.assertThat(quizResult).isEmpty();
        Assertions.assertThat(quizzesAll).isEmpty();
    }
}