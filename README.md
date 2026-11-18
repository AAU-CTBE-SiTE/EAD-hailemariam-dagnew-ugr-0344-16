# Lab 7: Spring Boot MySQL CRUD Application

A Spring Boot REST API for managing actors with MySQL persistence, Spring Data JPA, and Hibernate.

## Features
* Full CRUD operations for `Actor` entities (`/api/v1/actor`)
* MySQL database integration with automated table schema generation
* Built-in static frontend support for testing

## Quick Start
1. Configure database credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/actor_db?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
