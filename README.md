## Description
This repository contains a Java application designed for handling user authentication processes, including login, registration, password reset, and session management. It demonstrates the integration of JDBC for database operations, BCrypt for secure password hashing, and basic state management for user sessions in a console-based environment.

## Tech Stack Summary
```
1. Java: Core language used for building the application.
2. JDBC: Java Database Connectivity for database interactions.
3. MySQL: Relational database to store user data.
4. BCrypt: Library used for hashing and checking passwords securely.
5. Maven: Dependency management and project build.
```

## Principles Used
```
- **MVC Architecture**: Although primarily backend-focused, the application is structured in a way that respects the MVC (Model-View-Controller) design by separating the data access, business logic, and presentation layers.
- **SOLID Principles**: Adheres to SOLID principles for object-oriented design to enhance maintainability and scalability.
- **Security Best Practices**: Utilizes BCrypt for secure password hashing and employs prepared statements in JDBC to prevent SQL Injection attacks.
```

## Project Structure Diagram

```
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
```

## How To Run

```
Here's how you can run each Java file in VS Code terminal using Maven:

AppLogger.java:
No specific execution command as it's a utility class for logging.

ConsoleApplication.java:
Run the command: mvn exec:java@console

DatabaseConnection.java:
No specific execution command as it's a utility class for managing database connections.

LoginApplication.java:
Run the command: mvn exec:java@login

PasswordReset.java:
Run the command: mvn exec:java@reset

PasswordUtil.java:
No specific execution command as it's a utility class for password hashing.

RegistrationApplication.java:
Run the command: mvn exec:java@register
```
