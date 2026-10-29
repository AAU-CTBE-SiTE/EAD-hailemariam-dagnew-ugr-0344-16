# Spring Core - IoC and Bean Management Lab

This project demonstrates the core concepts of the Spring Framework, specifically focusing on Inversion of Control (IoC), Dependency Injection (DI), and Bean Management using XML configuration and Spring containers.

## Project Structure

- **`com.craft.Student`**: Main entity model containing student details, address, and enrolled subjects.
- **`com.craft.Address`**: Object dependency injected into the Student bean.
- **`com.craft.Subject`**: Collection component representing academic subjects.
- **`beansConfig.xml`**: XML configuration metadata defining beans, literal values, object references, and collection lists.
- **`com.craft.App`**: Launcher class initializing the `ClassPathXmlApplicationContext` container to retrieve and test beans.

## Key Features Implemented

1. **IoC Container Configuration**: Managing object lifecycles using `ApplicationContext` and `beansConfig.xml`[cite: 6].
2. **Dependency Injection**:
   - **Literal Dependency Injection**: Injecting primitive and string values (`id`, `name`, `age`)[cite: 15].
   - **Object Dependency Injection**: Injecting an `Address` bean using the `ref` attribute[cite: 18].
   - **Collection Dependency Injection**: Injecting a list of `Subject` beans using the `<list>` XML element[cite: 21, 22].
3. **Bean Scopes**: Configuring and testing bean scopes (`singleton` vs `prototype`)[cite: 25].
4. **Lombok Integration**: Utilizing `@Data` and `@ToString` for clean entity code[cite: 4, 8].

## Running the Application

1. Open the project in IntelliJ IDEA.
2. Ensure dependencies are resolved via Maven (`spring-context` and `lombok`)[cite: 4].
3. Run the `main` method in `App.java`.
