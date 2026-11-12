package com.example.springaop.service;

import com.example.springaop.exception.StudentNotFoundException;
import com.example.springaop.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    // Create Student
    public Student addStudent(Student student) {

        students.add(student);

        return student;
    }

    // Read All Students
    public List<Student> getAllStudents() {

        return students;
    }

    // Read Student By ID
    public Student getStudentById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    // Update Student
    public Student updateStudent(int id, Student updatedStudent) {

        Student student = getStudentById(id);

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());

        return student;
    }

    // Delete Student
    public String deleteStudent(int id) {

        Student student = getStudentById(id);

        students.remove(student);

        return "Student deleted successfully!";
    }
}