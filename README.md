# Trivia Back-end

Assignment for Quad Solutions.  

**Note: This application is complimented by the front-end: https://github.com/AliBikini/triviaFrontend**

## Installation

This application runs on localhost and uses port 8080 to receive http requests. Make sure the port is available on your system.  
To run this application, make sure you have the most recent version of Java (24) installed on your system.  

**To run this application:**  
Step 1: Open a command-line and navigate to the root folder of this project  
Step 2: Run the following commands depending on your system:
- WINDOWS: "**mvnw.cmd clean install**", then "**mvnw.cmd spring-boot:run**"
- LINUX: "**./mvnw clean install**", then "**./mvnw spring-boot:run**"  

If step 2 fails you can attempt to run the application with IntelliJ IDEA

## Endpoints
-   "localhost:8080/questions" (GET): receive a trivia quiz with 10 random questions
-   "localhost:8080/checkanswers/{idQuiz}" (POST): receive the results of the given answers corresponding to the given quiz with id "idQuiz".  
Answers must be submitted as a string array within the body of this request.  
    **Example of answers request body:**  
    [
        "True",
        "False",
        "An Answer",
        "Another Answer",
        "Etc",
        "Etc",
        "Etc",
        "Etc",
        "Etc",
        "Etc"
    ]
