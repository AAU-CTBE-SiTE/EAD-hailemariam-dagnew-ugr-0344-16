# Lab 2: JPA & Hibernate Student Management System

A Java application demonstrating ORM and database persistence using **JPA**, **Hibernate**, **MySQL**, and **Lombok**.

## 🚀 Features
* **Entity Mapping**: `Student` entity mapped to MySQL table using JPA & Lombok annotations.
* **Modular CRUD Operations**: Helper methods for `Create`, `Read`, `Update`, and `Delete`.
* **JPQL Queries**: Parameterized queries using `TypedQuery` (e.g., filtering by technology).

## 🛠️ Tech Stack
* Java 17+ | Apache Maven
* Jakarta Persistence API (JPA 3.0)
* Hibernate ORM 6.4.4.Final
* MySQL Server

## 📁 Project Structure
```text
JPA/
├── src/main/java/com/craft/
│   ├── App.java          # CRUD methods & main launcher
│   └── Student.java      # JPA Entity class
├── src/main/resources/META-INF/
│   └── persistence.xml   # Database configuration
└── pom.xml               # Dependencies
```

## ⚙️ Setup & Run
1. **Create Database**:
   ```sql
   CREATE DATABASE jpadb1;
   ```
2. **Configure Credentials**: Update MySQL user/password in `persistence.xml`.
3. **Run**: Execute `App.java` main method to run all CRUD lifecycle operations.
