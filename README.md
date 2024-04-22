# Java-Client-Server-Auth-Application

## Description
This repository contains a Java application designed for handling user authentication processes, including login, registration, password reset, and session management. It demonstrates the integration of JDBC for database operations, BCrypt for secure password hashing, and basic state management for user sessions in a console-based environment.

## Tech Stack Summary
- **Java**: Core language used for building the application.
- **JDBC**: Java Database Connectivity for database interactions.
- **MySQL**: Relational database to store user data.
- **BCrypt**: Library used for hashing and checking passwords securely.
- **Maven**: Dependency management and project build.

## Principles Used
- **MVC Architecture**: Although primarily backend-focused, the application is structured in a way that respects the MVC (Model-View-Controller) design by separating the data access, business logic, and presentation layers.
- **SOLID Principles**: Adheres to SOLID principles for object-oriented design to enhance maintainability and scalability.
- **Security Best Practices**: Utilizes BCrypt for secure password hashing and employs prepared statements in JDBC to prevent SQL Injection attacks.

## Project Structure Diagram

Project_Root/
│
├── src/
│ └── main/
│  └── java/
│   └── com/
│    └── example/
│     ├── AppLogger.java # Handles application logging.
│     ├── ConsoleApplication.java # Manages console interactions.
│     ├── DatabaseConnection.java # Manages database connections.
│     ├── LoginApplication.java # Handles user login functionality.
│     ├── PasswordReset.java # Manages password resetting.
│     ├── PasswordUtil.java # Provides password hashing utilities.
│     └── RegistrationApplication.java # Manages user registration.
│
└── pom.xml # Maven project file.
