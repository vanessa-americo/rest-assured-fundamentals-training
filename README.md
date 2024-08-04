## Learning from Udemy Course - REST Assured Fundamentals 2022

- The project is using RestAssured to test a set of APIs.
- Runs in the Github Actions pipeline.
- Allure reports

Test Execution reports are available at <https://vanessa-americo.github.io/rest-assured-fundamentals-training>

## Using

- [Java](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) (v21.0.3)
- [RestAssured](https://mvnrepository.com/artifact/io.rest-assured/rest-assured) (v5.4.0)
- [RestAssured - Schema Validator](https://mvnrepository.com/artifact/io.rest-assured/json-schema-validator) (v5.3.0)
- [JUnit](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter) (v5.10.2)
- [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind) (2.14.2)
- [SLF4J](https://mvnrepository.com/artifact/org.slf4j/slf4j-api) (2.1.0-alpha1)
- [Allure](https://mvnrepository.com/artifact/io.qameta.allure/allure-rest-assured) (2.25.0)

### Project Structure
    .
    ├── .github
    │   ├── workflows                                     
    │   │   └──github_workflow.yml             # CI Configuration File
    ├── src
    │   ├── test                     
    │   │   ├── java
    │   │   │   └── config          
    │   │   │       └── **.java     # API Test Configuration Files 
    │   │   │   └── objects          
    │   │   │       └── **.java     # Java Objects (POJOs)
    │   │   │   └── **.java         # API Tests  
    │   │   ├── resources                                     
    │   │   │   └── **.json         # JSON Schema for validation
    │   │   │   └── **.xsd          # XML Schema for validation
    │   │   │   └── **.properties   # Allure Report Properties
    └── ...


## How to run tests locally

Make Sure that the machines Java version is 21 or higher

```bash
  java -version
```

Clone the project

```bash
  git clone https://github.com/vanessa-americo/rest-assured-fundamentals-training
```

Go to the project folder

```bash
  cd /your/project/path
```

Add API Token

```bash
  Generate and add a token from https://www.football-data.org/ on src/test/java/config/FootballConfig.java
  Remove the @Disabled annotation from src/test/java/FootballTest.java
```

Run the tests

```bash
  mvn clean test
```

## Test and Deploy

The project uses Github Actions to run the tests on the CI.

    - The tests run after any push to main branch

## Allure Reports

After executing the tests, run the following command on the terminal to open the Allure report with the test execution results:

```bash
  allure serve target\allure-results
```

## Authors

- [@vanessa-americo](https://github.com/vanessa-americo)