// RegistrationApplication.java

package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Handles the registration of new users, including collecting and storing their
 * username,
 * password, and security question/answer for account recovery.
 */
public class RegistrationApplication {

    /**
     * The main method initiates the user registration process by prompting for user
     * details,
     * including security information.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Collect user input for username
            System.out.println("Enter new username:");
            String username = scanner.nextLine();

            // Collect user input for password
            System.out.println("Enter new password:");
            String password = scanner.nextLine();

            // Collect user input for security question
            System.out.println("Enter a security question (e.g., 'What is your favorite color?'):");
            String securityQuestion = scanner.nextLine();

            // Collect user input for security answer and hash it
            System.out.println("Enter an answer for your security question:");
            String securityAnswer = PasswordUtil.hashPassword(scanner.nextLine()); // Hash the security answer

            // Register the user with the collected details
            registerUser(username, password, securityQuestion, securityAnswer);
        }
    }

    /**
     * Registers a new user in the database with a username, hashed password,
     * security question, and hashed answer.
     * 
     * @param username         The username chosen by the user.
     * @param password         The user's password to hash and store securely.
     * @param securityQuestion The security question chosen by the user for password
     *                         recovery.
     * @param securityAnswer   The hashed answer to the security question for secure
     *                         storage.
     */
    private static void registerUser(String username, String password, String securityQuestion, String securityAnswer) {
        String hashedPassword = PasswordUtil.hashPassword(password); // Hash the password for secure storage
        String query = "INSERT INTO users (username, password, security_question, security_answer) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); // Attempt to connect to the database
                PreparedStatement stmt = conn.prepareStatement(query)) { // Prepare the SQL statement
            stmt.setString(1, username); // Set the username in the query
            stmt.setString(2, hashedPassword); // Set the hashed password in the query
            stmt.setString(3, securityQuestion); // Set the security question in the query
            stmt.setString(4, securityAnswer); // Set the hashed security answer in the query

            int result = stmt.executeUpdate(); // Execute the update and get the result
            if (result > 0) {
                System.out.println("User registered successfully!");
            } else {
                System.out.println("User registration failed!");
            }
        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
        }
    }
}