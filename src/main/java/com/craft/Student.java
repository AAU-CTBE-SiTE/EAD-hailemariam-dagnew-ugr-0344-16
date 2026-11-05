package com.craft;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Data
@ToString
@Component("student") // Registers this class as a bean named "student"
public class Student {
    private int id = 101;
    private String name = "John Doe";
    private int age = 20;

    @Autowired
    private Address address;

    @Autowired
    private List<Subject> subjects;

    public void getDetails() {
        System.out.println("Student Name: " + this.getName());
        int index = 1;
        if (subjects != null) {
            for (Subject subject : subjects) {
                System.out.println("Subject " + index + ": " + subject.getSubjectName());
                index++;
            }
        }
    }
}