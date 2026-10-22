package com.craft;

import jakarta.persistence.*;
import java.util.List;

public class App {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createStudent(Student student) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static List<Student> getStudentsByTech(String tech) {
        EntityManager em = getEntityManager();
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s WHERE s.tech = :tech", Student.class
        );
        query.setParameter("tech", tech);
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }

    public static Student getStudentById(int id) {
        EntityManager entityManager = getEntityManager();
        Student student = entityManager.find(Student.class, id);
        entityManager.close();
        return student;
    }

    public static void updateStudentName(String newName, int id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            student.setName(newName);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void deleteStudentById(int id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void main(String[] args) {

        Student student1 = new Student();
        student1.setName("Mohammed Ali");
        student1.setTech("Python");
        createStudent(student1);

        Student s1 = new Student();
        s1.setName("John Snow");
        s1.setTech("Java");
        createStudent(s1);

        Student s3 = new Student();
        s3.setName("Abebe");
        s3.setTech("C++");
        createStudent(s3);

        System.out.println("Fetched ID 1: " + getStudentById(1));


        updateStudentName("Alemayehu", 1);
        System.out.println("Updated ID 1: " + getStudentById(1));

        EntityManager em = getEntityManager();
        List<Student> students = em.createQuery("FROM Student s", Student.class).getResultList();
        System.out.println("All Students:");
        students.forEach(System.out::println);
        em.close();


        List<Student> javaStudents = getStudentsByTech("Java");
        System.out.println("Java Students: " + javaStudents);


        deleteStudentById(2);
        System.out.println("After deleting ID 2, student count/check: " + getStudentById(2));
        emf.close();
    }
}
