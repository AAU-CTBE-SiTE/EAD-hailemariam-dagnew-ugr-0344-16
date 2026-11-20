# Lab 8: RESTful CRUD API (Spring Boot & MySQL)

RESTful CRUD API for managing student records (`Student`) built with Spring Boot, Spring Data JPA, Lombok, and MySQL (`student_db`).

## Tech Stack
- **Java & Framework:** Java 23, Spring Boot 3.3.5[cite: 1, 2]
- **Persistence:** Spring Data JPA, MySQL (`student_db`, `students` table)[cite: 1, 2]
- **Utilities:** Lombok[cite: 1, 2]

## Project Structure
- **Model (`Student`):** Entity fields (`id`, `firstName`, `lastName`, `email`, `department`) mapped to the `students` table[cite: 1, 2].
- **Repository (`StudentRepository`):** Extends `JpaRepository<Student, Long>` for persistence operations[cite: 1, 2].
- **Service (`StudentService`):** Handles CRUD logic and returns `null` (`.orElse(null)`) when a requested ID is not found[cite: 1, 2].
- **Controller (`StudentController`):** Exposes `/api/students` REST endpoints[cite: 1, 2].

## Running the Application (`cmd.exe`)
Start the server on port `8080`:

```cmd
mvnw.cmd spring-boot:run
