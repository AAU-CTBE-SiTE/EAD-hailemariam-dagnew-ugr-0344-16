package com.craft;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.craft") // Scans for @Component classes like Student and Address
public class AppConfig {

    @Bean(name = "math")
    public Subject mathSubject() {
        Subject subject = new Subject();
        subject.setSubjectName("Maths 101");
        return subject;
    }

    @Bean(name = "java")
    public Subject javaSubject() {
        Subject subject = new Subject();
        subject.setSubjectName("Java EE");
        return subject;
    }
}