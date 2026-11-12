# Spring AOP Lab – Student Management System

## 1. Project Overview

This project demonstrates Aspect-Oriented Programming (AOP) using Spring Boot.

It implements a Student Management System that performs CRUD (Create, Read, Update, Delete) operations using an in-memory ArrayList instead of a database.

Spring AOP separates cross-cutting concerns, particularly logging, exception monitoring, and execution-time measurement, from the main business logic.

The application also includes custom exception handling and automated tests to verify AOP functionality.

## 2. Technologies Used

- Java 25 (latest LTS)
- Spring Boot 4.1.2
- Spring AOP / AspectJ
- Spring Web MVC
- SLF4J Logging
- Maven
- Postman
- JUnit 5
- IntelliJ IDEA

## 3. Project Structure

```text
src/
├── main/
│   └── java/
│       └── com/example/springaop/
│           ├── SpringAopLabApplication.java
│           ├── aspect/
│           │   └── LoggingAspect.java
│           ├── controller/
│           │   └── StudentController.java
│           ├── exception/
│           │   ├── GlobalExceptionHandler.java
│           │   └── StudentNotFoundException.java
│           ├── model/
│           │   └── Student.java
│           └── service/
│               └── StudentService.java
└── test/
    └── java/
        └── com/example/springaop/
            └── SpringAopLabApplicationTests.java
```

## 4. AOP Concepts Implemented

The project demonstrates the following AOP concepts:

- **Aspect:** The `LoggingAspect` class contains cross-cutting logging functionality.
- **Advice:** Defines when the aspect's behavior should execute.
- **Join Point:** A method execution intercepted by Spring AOP.
- **Pointcut:** Defines which methods should be intercepted.
- **Target Object:** The `StudentService` class whose methods are intercepted.
- **Weaving:** The process of connecting an aspect with target methods. Spring AOP uses runtime proxies.

### Advice Types

The project implements all five major Spring AOP advice types:

| Advice | Annotation | Purpose |
|---|---|---|
| Before Advice | `@Before` | Executes before a service method. |
| After Advice | `@After` | Executes after a service method, whether successful or unsuccessful. |
| After Returning Advice | `@AfterReturning` | Executes when a service method completes successfully. |
| After Throwing Advice | `@AfterThrowing` | Executes when a service method throws an exception. |
| Around Advice | `@Around` | Wraps method execution and measures execution time. |

### Reusable Pointcut

The project defines a reusable pointcut:

```java
@Pointcut("execution(* com.example.springaop.service.*.*(..))")
public void serviceMethods() {
}
```

This selects methods inside the service package.

All five advice methods reference this pointcut instead of repeating the expression.

### Logging

SLF4J is used for structured logging.

The aspect records:
- Service method execution.
- Successful method completion.
- Exceptions.
- Execution time in milliseconds.

## 5. Student Management REST API

The application provides the following REST API endpoints:

| Operation | HTTP Method | Endpoint |
|---|---|---|
| Add Student | POST | `/students` |
| Get All Students | GET | `/students` |
| Get Student by ID | GET | `/students/{id}` |
| Update Student | PUT | `/students/{id}` |
| Delete Student | DELETE | `/students/{id}` |

### Example JSON Request

```json
{
  "id": 1,
  "name": "Abebe",
  "email": "abebe@gmail.com"
}
```

### Example API Responses

**Successful request:** Returns the requested student information or operation result.

**Student not found:** Returns HTTP 404 Not Found.

```json
{
  "error": "Student with ID 999 not found"
}
```

## 6. Exception Handling

The application implements custom exception handling.

- `StudentNotFoundException` is thrown when a requested student does not exist.
- `GlobalExceptionHandler` handles this exception using `@RestControllerAdvice`.
- The API returns HTTP 404 instead of a generic HTTP 500 response.

Spring AOP also detects the exception through `@AfterThrowing`.

## 7. How to Run the Project

### Prerequisites

- Java JDK 25
- IntelliJ IDEA
- Maven Wrapper (included in the project)

### Steps

1. Clone or download the project.
2. Open the project in IntelliJ IDEA.
3. Allow Maven to download the required dependencies.
4. Run `SpringAopLabApplication.java`.

Alternatively, run the following command in the project directory:

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts at:

`http://localhost:8080`

## 8. Testing

The REST API endpoints can be tested using Postman.

The project also includes automated tests using JUnit 5 and Spring Boot testing support.

Two tests are implemented:

| Test | Purpose |
|---|---|
| `contextLoads()` | Verifies that the Spring application context loads successfully. |
| `aopShouldLogServiceMethod()` | Verifies that AOP logging executes when a service method is called. |

Run the automated tests using:

```powershell
.\mvnw.cmd clean test
```

Expected result:

```text
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## 9. Storage Note

Student records are stored in an in-memory `ArrayList`.

Therefore, all student records are cleared when the application restarts.

No external database is required.

## 10. Conclusion

This project demonstrates how Spring AOP separates cross-cutting concerns from business logic.

By implementing all five advice types, reusable pointcuts, SLF4J logging, custom exception handling, and automated tests, the application demonstrates method interception, exception monitoring, and execution-time measurement while maintaining an organized Student Management System.