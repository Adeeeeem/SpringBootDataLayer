# SpringBoot Data Layer Project - Create MySQL DB

## Overview

This project is a demonstration of using Spring Boot to create a data layer for a MySQL database. The project includes the creation of entities, repositories, and controllers for managing entities and views. The database schema is automatically generated, and CRUD (create, read, update, delete) operations are implemented. Operations like ManyToOne and OneToMany are also included.
The project was created using Spring Tool Suite (STS) with Maven 2.6.5 and Java version 17.

This Spring Boot project is designed to create and manage a MySQL database using Spring Data JPA. The application allows you to create, read, update, and delete entities with a user interface built using Thymeleaf. The following features have been covered as part of this project:

- Creating entities with `@Entity` annotations
- Creating repositories with `@Repository` annotations
- Creating controllers with `@Controller` and `@RequestMapping` annotations
- Defining views with Thymeleaf templates
- Handling `GET` and `POST` requests with `@GetMapping` and `@PostMapping` annotations
- Configuring MySQL database connection with `application.properties`
- Performing CRUD operations on the database

![Plan of the bootcamp](./Plan.png)

## Getting Started

To run the project, you will need to have Maven and Java JDK 17 installed on your system. You can use your preferred Integrated Development Environment (IDE) to run the project. Here are the steps to run the project in Spring Tool Suite (STS):

### Step 1

Open STS and import the project.

### Step 2

Right-click on the project and select "Run As" > "Spring Boot App".

### Step 3

Once the application is running, you can access it by navigating to http://localhost:8081 in your web browser.

Note that the port number has been changed from the default 8080 to 8081.

##How to configure MySQL username, password and database name

To configure MySQL username, password and database name, open the application.properties file in the project and update the following properties :

spring.datasource.username: replace with your MySQL username
spring.datasource.password: replace with your MySQL password
spring.datasource.url: replace SpringBootDB with your desired database name.

## Contributing

If you would like to contribute to the project, please fork the repository and create a pull request with your proposed changes. Be sure to follow the project's coding standards and include appropriate tests with your changes.

## Dependencies

The following dependencies were used in the creation of this project:

- Spring Boot DevTools
- Spring Web Starter
- Thymeleaf
- Spring Data JPA
- MySQL Driver

## Available Endpoints

http://localhost:8081/ : welcome interface
http://localhost:8081/provider/list : list of providers interface
http://localhost:8081/provider/add : add a provider interface

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. Feel free to use this code in your own projects or modify it to fit your specific needs.
