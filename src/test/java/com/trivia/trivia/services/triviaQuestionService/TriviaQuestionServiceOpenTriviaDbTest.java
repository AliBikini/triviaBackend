package com.trivia.trivia.services.triviaQuestionService;

import com.trivia.trivia.exceptions.OpenTriviaApiConnectionException;
import com.trivia.trivia.exceptions.OpenTriviaApiDataProcessingException;
import com.trivia.trivia.models.TriviaQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
class TriviaQuestionServiceOpenTriviaDbTest {
    @Autowired
    @InjectMocks
    private TriviaQuestionServiceOpenTriviaDb triviaQuestionService;

    @Mock
    private OpenTriviaDbWebClient webClient;

    @Test
    void getRandomTriviaQuestions_should_returnListOfTriviaQuestions() throws OpenTriviaApiConnectionException, OpenTriviaApiDataProcessingException {
        String jsonOpenTriviaApiResultMock = """
                    {"response_code":0,"results":[{"type":"multiple","difficulty":"hard","category":"Entertainment: Cartoon &amp; Animations","question":"In the &quot;Star Wars&quot; universe, what species is Grand Admiral Thrawn?","correct_answer":"Chiss","incorrect_answers":["Gungans","Pantorans","Twi&#039;lek"]},{"type":"multiple","difficulty":"hard","category":"Entertainment: Video Games","question":"In Terraria, which debuff does the ankh charm not provide immunity to?","correct_answer":"Venom","incorrect_answers":["Cursed","Bleeding","Slow"]},{"type":"multiple","difficulty":"medium","category":"Vehicles","question":"Which of the following car models has been badge-engineered (rebadged) the most?","correct_answer":"Isuzu Trooper","incorrect_answers":["Holden Monaro","Suzuki Swift","Chevy Camaro"]},{"type":"multiple","difficulty":"medium","category":"Entertainment: Music","question":"Which of these is NOT a song on The Beatles&#039; 1968 self titled album, also known as the White album?","correct_answer":"Being For The Benefit Of Mr. Kite!","incorrect_answers":["Why Don&#039;t We Do It in the Road?","Everybody&#039;s Got Something to Hide Except Me and My Monkey","The Continuing Story of Bungalow Bill"]},{"type":"multiple","difficulty":"medium","category":"Science: Computers","question":"The teapot often seen in many 3D modeling applications is called what?","correct_answer":"Utah Teapot","incorrect_answers":["Pixar Teapot","3D Teapot","Tennessee Teapot"]},{"type":"multiple","difficulty":"hard","category":"Science &amp; Nature","question":"An organic compound is considered an alcohol if it has what functional group?","correct_answer":"Hydroxyl","incorrect_answers":["Carbonyl","Alkyl","Aldehyde"]},{"type":"boolean","difficulty":"easy","category":"Entertainment: Music","question":"In 1993, Prince changed his name to an unpronounceable symbol because he was unhappy with his contract with Warner Bros.","correct_answer":"True","incorrect_answers":["False"]},{"type":"multiple","difficulty":"easy","category":"Science: Computers","question":"The programming language &#039;Swift&#039; was created to replace what other programming language?","correct_answer":"Objective-C","incorrect_answers":["C#","Ruby","C++"]},{"type":"multiple","difficulty":"easy","category":"Entertainment: Video Games","question":"In &quot;World of Warcraft,&quot; which of the following can be found outside of every instanced dungeon?","correct_answer":"A Meeting Stone","incorrect_answers":["A Summoning Stone","A Gathering Stone","A Hearthstone"]},{"type":"boolean","difficulty":"hard","category":"Science: Mathematics","question":"In Topology, the complement of an open set is a closed set.","correct_answer":"True","incorrect_answers":["False"]}]}
                """;

        when(webClient.getRandomQuestions(anyString())).thenReturn(jsonOpenTriviaApiResultMock);

        List<TriviaQuestion> questionList = triviaQuestionService.getRandomTriviaQuestions();
        assertTrue(!questionList.isEmpty());
    }

    @Test
    void getRandomTriviaQuestions_should_throwCorrectErrorWhenOpenTriviaApiDataIsInvalid() {
        String invalidJsonOpenTriviaApiResultMock = """
                { broken data }
                """;

        when(webClient.getRandomQuestions(anyString())).thenReturn(invalidJsonOpenTriviaApiResultMock);

        Exception exception = assertThrows(OpenTriviaApiDataProcessingException.class, () -> triviaQuestionService.getRandomTriviaQuestions());
        assertTrue(exception.getMessage().contains("Could not process received Open Trivia Api data"));
    }

    @Test
    void getRandomTriviaQuestions_should_throwCorrectErrorWhenOpenTriviaApiConnectionError() {
        when(webClient.getRandomQuestions(anyString())).thenThrow(RuntimeException.class);

        Exception exception = assertThrows(OpenTriviaApiConnectionException.class, () -> triviaQuestionService.getRandomTriviaQuestions());
        assertTrue(exception.getMessage().contains("Open Trivia Api connection error"));
    }
}