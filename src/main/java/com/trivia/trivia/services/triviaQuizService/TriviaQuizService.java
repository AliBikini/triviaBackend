package com.trivia.trivia.services.triviaQuizService;

import com.trivia.trivia.models.TriviaQuestion;
import com.trivia.trivia.models.TriviaQuiz;
import com.trivia.trivia.models.dtos.TriviaQuizResultsDTO;
import com.trivia.trivia.repos.trivaQuizRepo.ITriviaQuizRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TriviaQuizService {
    private ITriviaQuizRepo repo;

    public TriviaQuizService(ITriviaQuizRepo repo) {
        this.repo = repo;
    }

    public Optional<TriviaQuiz> create(List<TriviaQuestion> triviaQuestions) {
        return repo.create(triviaQuestions);
    }

    public Optional<TriviaQuiz> get(String id) {
        return repo.get(id);
    }

    public List<TriviaQuiz> getAll() {
        return repo.getAll();
    }
}
