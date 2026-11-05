package com.craft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // Initialize the Spring IoC Container using the Java Configuration class
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve the student bean from the container
        Student student = context.getBean("student", Student.class);

        // Print details
        System.out.println(student);
        student.getDetails();
    }
}