# Spring Core IoC & Bean Management Lab (Lab-4)

This project demonstrates Spring Framework's Inversion of Control (IoC) and Dependency Injection (DI) using Java annotations instead of XML configuration.

## Project Structure
* `src/main/java/com/craft/`
  * `App.java` - Main launcher class
  * `AppConfig.java` - Java configuration class (`@Configuration`, `@Bean`, `@ComponentScan`)
  * `Student.java` - Entity bean (`@Component`) with object and collection injection (`@Autowired`)
  * `Address.java` - Nested object dependency (`@Component`)
  * `Subject.java` - Subject model bean

## Technologies Used
* Java 19
* Spring Framework (Context 6.2.0)
* Project Lombok (`@Data`, `@ToString`)
* Maven

## How to Run
1. Open the project in IntelliJ IDEA.
2. Ensure Maven dependencies are loaded from `pom.xml`.
3. Run `App.java` to initialize the Spring container and execute bean retrieval.
