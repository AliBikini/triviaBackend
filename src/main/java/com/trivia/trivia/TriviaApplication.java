package com.trivia.trivia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TriviaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TriviaApplication.class, args);
	}
}
