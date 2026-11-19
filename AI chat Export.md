# AI-Guided Laboratory Chat Transcript

- **Course / Lab:** Building a RESTful CRUD API with Spring Boot, Spring Data JPA and MySQL
- **Institution:** Addis Ababa University, College of Technology and Built Environment, School of Information Technology and Engineering (SITE)
- **Runnable Entry Point:** `CrudApiApplication.java` (`com.site.crudapi`)
- **Database:** MySQL (`student_db`, table: `students`)

---

## Phase 1: Introduction & Objectives

**Student:** Let's start the lab on building a RESTful CRUD API with Spring Boot, Spring Data JPA, and MySQL. Can you introduce the topic, explain why RESTful APIs matter in real-world backend systems, list the learning objectives, and check what prerequisites we need before coding?

**AI Instructor:** In a RESTful API, resources (like a `Student`) are identified by URIs and manipulated through standard HTTP methods (`GET`, `POST`, `PUT`, `DELETE`) using stateless client-server communication, JSON payloads, and HTTP status codes. In real-world backend systems, RESTful APIs decouple clients from database schemas and business logic so web apps, mobile apps, and other services can interact with data consistently.

### Learning Objectives
1. Explain the purpose of building a RESTful CRUD API and its real-world backend role.
2. Describe how Spring Boot components, annotations, interfaces, and classes work internally.
3. Configure a Maven + Java Spring Boot project and `application.properties` for MySQL.
4. Implement the layered CRUD feature incrementally and explain each piece.
5. Run and verify all endpoints using `curl` or Postman.
6. Diagnose and resolve errors by reading stack traces and root causes.
7. Submit source code, execution evidence, and chat history in Git with commit message `ai-assisted-restful-api`.

**Prerequisites:** Java and OOP basics, relational database/MySQL basics, and Spring Initializr + IntelliJ IDEA.

Before we write code, explain in your own words why statelessness and resource URIs matter for a client calling the API.

---

## Phase 2: Technical Background

**Student:** Statelessness means the server does not keep client session state between requests, and resource URIs name resources (like `students`) while HTTP methods specify the action. How do Spring Boot, Spring MVC, and Spring Data JPA work internally for a `Student` entity (`id`, `firstName`, `lastName`, `email`, `department`), given our constraints of no DTOs, no `ResponseEntity`, no custom exception handlers (returning `null` when not found), and using Lombok?

**AI Instructor:** `@SpringBootApplication` enables component scanning and auto-configuration, starting embedded Tomcat on port `8080`. `@RestController` maps HTTP requests to handler methods and uses Jackson to serialize returned `Student` or `List<Student>` objects into JSON (or an empty body for `null`/`void`), without wrapping responses in `ResponseEntity` or mapping through DTOs.

Controller methods call a Service layer, which calls a repository extending `JpaRepository<Student, Long>`. Spring Data JPA generates the repository proxy at startup using Hibernate and JDBC. Lombok generates getters, setters, and constructors at compile time to reduce boilerplate, while JPA requires a no-arg constructor to instantiate entities when reading rows from MySQL. Because custom exception handlers are omitted per the lab rules, missing lookups return `null`.

What happens if a JPA `@Entity` has a parameterized constructor from Lombok but no no-arg constructor?

---

## Phase 3: Environment & Project Configuration

**Student:** Without a no-arg constructor, Hibernate cannot instantiate the entity via reflection when loading records. For environment and configuration, we created the Maven + Java project with Spring Web, Spring Data JPA, MySQL Driver, and Lombok only, and configured `application.properties` (not `application.yml`). Why use `application.properties` and how do `spring.datasource.*` and `spring.jpa.*` properties work?

**AI Instructor:** Keeping configuration in `application.properties` follows the lab constraint and separates database connection details from compiled Java code. `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` configure HikariCP to connect to MySQL, while `spring.jpa.hibernate.ddl-auto` controls how Hibernate manages schema tables.

Verify that `pom.xml` includes only the four required dependencies, `application.properties` targets `student_db`, and `CrudApiApplication.java` is ready in IntelliJ IDEA. Confirm when ready.

---

## Phase 4: Step-by-Step Implementation

### Step 4.1: `Student` Entity (`Model`)

**Student:** Configuration is ready and MySQL is set up for `student_db`. Let's implement the `Student` model first.

**AI Instructor:**
- **Purpose:** Define the single JPA entity representing a student record with `id`, `firstName`, `lastName`, `email`, and `department` using Lombok and no DTOs.
- **File & Package:** `src/main/java/com/site/crudapi/model/Student.java` (`package com.site.crudapi.model`)

```java
package com.site.crudapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String department;
}
```

**Explanation & Interactions:** `@Entity` and `@Table(name = "students")` map the class to the `students` table, and `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` let MySQL auto-generate primary keys. Lombok's `@Data`, `@NoArgsConstructor`, and `@AllArgsConstructor` generate getters, setters, `equals`/`hashCode`/`toString`, and constructors. The repository and service layers pass `Student` objects directly.

Type and compile `Student.java`, then confirm before moving to the repository.

---

### Step 4.2: `StudentRepository` (`Repository`)

**Student:** Step complete. `Student.java` compiles cleanly with `id`, `firstName`, `lastName`, `email`, and `department`. Proceed to the next step.

**AI Instructor:**
- **Purpose:** Provide data-access CRUD operations for `Student` without writing boilerplate SQL or DAO implementations.
- **File & Package:** `src/main/java/com/site/crudapi/repository/StudentRepository.java` (`package com.site.crudapi.repository`)

```java
package com.site.crudapi.repository;

import com.site.crudapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
```

**Explanation & Interactions:** Extending `JpaRepository<Student, Long>` supplies `save`, `findAll`, `findById`, `deleteById`, and `existsById` for `Student` entities with `Long` IDs. `StudentService` injects `StudentRepository` to persist and query records.

Implement `StudentRepository.java`, verify compilation, and confirm.

---

### Step 4.3: `StudentService` (`Service`)

**Student:** Step complete. `StudentRepository.java` compiles cleanly. Proceed to the next step.

**AI Instructor:**
- **Purpose:** Encapsulate create, retrieve all, retrieve by id, update, and delete logic between the controller and repository, returning `null` when a record is not found and avoiding custom exceptions or DTOs.
- **File & Package:** `src/main/java/com/site/crudapi/service/StudentService.java` (`package com.site.crudapi.service`)

```java
package com.site.crudapi.service;

import com.site.crudapi.model.Student;
import com.site.crudapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = studentRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setFirstName(updatedStudent.getFirstName());
        existing.setLastName(updatedStudent.getLastName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setDepartment(updatedStudent.getDepartment());
        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        }
    }
}
```

**Explanation & Interactions:** `@Service` registers the bean, constructor injection wires `StudentRepository`, and `.orElse(null)` returns `null` when a student ID is not present. `StudentController` delegates HTTP operations to these methods.

Implement `StudentService.java`, verify compilation, and confirm.

---

### Step 4.4: `StudentController` (`Controller`)

**Student:** Step complete. `StudentService.java` compiles cleanly. Proceed to the next step.

**AI Instructor:**
- **Purpose:** Expose RESTful CRUD endpoints returning `Student`, `List<Student>`, or `void` directly without DTOs, `ResponseEntity`, or `@ExceptionHandler` / `@ControllerAdvice`.
- **File & Package:** `src/main/java/com/site/crudapi/controller/StudentController.java` (`package com.site.crudapi.controller`)

```java
package com.site.crudapi.controller;

import com.site.crudapi.model.Student;
import com.site.crudapi.service.StudentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
```

**Explanation & Interactions:** `@RestController` handles REST endpoints, `@RequestBody` maps incoming JSON to `Student`, and `@PathVariable` extracts `{id}` from the URL path.

Implement `StudentController.java` and confirm once it compiles.

---

## Phase 5: Execution & Verification

**Student:** Step complete. `StudentController.java` compiles cleanly. How do I run and verify all five CRUD endpoints?

**AI Instructor:** Run `CrudApiApplication.java` in IntelliJ IDEA and confirm Tomcat starts on port `8080` and `Started CrudApiApplication` appears. Then test with `curl` or Postman:

- **Create (`POST`):**
  ```bash
  curl -X POST http://localhost:8080/api/students \
    -H "Content-Type: application/json" \
    -d '{"firstName":"Selam","lastName":"Tadesse","email":"selam@example.com","department":"Software Engineering"}'
  ```
  *Expected:* Saved `Student` JSON with generated `id`.

- **Retrieve All (`GET`):**
  ```bash
  curl http://localhost:8080/api/students
  ```
  *Expected:* JSON list of `Student` records.

- **Retrieve by ID (`GET`):**
  ```bash
  curl http://localhost:8080/api/students/1
  ```
  *Expected:* `Student` JSON if found, or empty response (`null`) if not found.

- **Update (`PUT`):**
  ```bash
  curl -X PUT http://localhost:8080/api/students/1 \
    -H "Content-Type: application/json" \
    -d '{"firstName":"Selam","lastName":"Tadesse","email":"selam@example.com","department":"Information Systems"}'
  ```
  *Expected:* Updated `Student` JSON.

- **Delete (`DELETE`):**
  ```bash
  curl -X DELETE http://localhost:8080/api/students/1
  ```
  *Expected:* Empty response (`void`); a subsequent `GET /api/students/1` returns empty (`null`).

Verify rows in MySQL with `SELECT * FROM students;` and capture screenshots of execution and API responses.

---

## Phase 6: Practical Exercises

**Student:** What basic, intermediate, and real-world exercises can I complete to reinforce these concepts?

**AI Instructor:**
- **Basic:** Create a student via `POST`, list all students via `GET`, and verify the new row in the MySQL `students` table.
- **Intermediate:** Request `GET /api/students/999` and `PUT /api/students/999` for a non-existent ID to observe `null` handling without custom exception classes.
- **Real-World Scenario:** A university student registry backend supporting student enrollment, lookup by ID, department updates, and record removal.

---

## Phase 7: Troubleshooting

**Student:** While running the project, I encountered a run configuration issue on `Current File`, MySQL `Access denied for user 'root'@'localhost'` (SQL Error `1045`), and `Unknown database 'student_db'` (SQL Error `1049`). How do I read and resolve these errors?

**AI Instructor:** Check the `Caused by:` lines near the bottom of the stack trace:
1. **Run configuration / project root:** Run `CrudApiApplication` directly (not `Current File` on a test class) and link `pom.xml` as a Maven project if needed.
2. **`Access denied for user 'root'@'localhost'` (Error `1045`):** Fix `spring.datasource.username` and `spring.datasource.password` in `application.properties` to match your MySQL credentials.
3. **`Unknown database 'student_db'` (Error `1049`):** Run `CREATE DATABASE student_db;` in MySQL before starting the application.

---

## Phase 8: Deliverables

**Student:** What are the deliverables and viva review questions before I commit and push to GitHub?

**AI Instructor:**

### Deliverables
- **Source code:** `Student.java`, `StudentRepository.java`, `StudentService.java`, `StudentController.java`, `CrudApiApplication.java`, `pom.xml`, and `application.properties`.
- **Execution evidence:** Screenshots/outputs of the running app and CRUD API requests.
- **Git & chat transcript:** Commit code and chat transcript to your assigned GitHub repository using commit message `ai-assisted-restful-api`.


