package com.trivia.trivia.services.triviaQuestionService;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.trivia.trivia.models.TriviaQuestion;

import java.io.IOException;
import java.util.ArrayList;

public class OpenTriviaDbTriviaQuestionsDeserializer extends JsonDeserializer<TriviaQuestion[]> {

    @Override
    public TriviaQuestion[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        ArrayList<TriviaQuestion> questions = new ArrayList<TriviaQuestion>();

        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        JsonNode results = node.get("results");

        for (int i = 0; i < results.size(); i++) {
            JsonNode objNode = results.get(i);
            String type = objNode.get("type").asText();
            String difficulty = objNode.get("difficulty").asText();
            String category = objNode.get("category").asText();
            String question = objNode.get("question").asText();
            String correctAnswer = objNode.get("correct_answer").asText();
            ArrayList<String> incorrectAnswers = new ArrayList<String>();

            JsonNode nodesIncorrectAnswers = objNode.get("incorrect_answers");

            for (JsonNode nodeIncorrectAnswer : nodesIncorrectAnswers)
            {
                incorrectAnswers.add(nodeIncorrectAnswer.asText());
            }

            String[] incorrectAnswersArray = new String[incorrectAnswers.size()];
            incorrectAnswersArray = incorrectAnswers.toArray(incorrectAnswersArray);

            questions.add(new TriviaQuestion(i, type, difficulty, category, question, correctAnswer, incorrectAnswersArray));
        }

        return questions.toArray(new TriviaQuestion[0]);
    }
}
