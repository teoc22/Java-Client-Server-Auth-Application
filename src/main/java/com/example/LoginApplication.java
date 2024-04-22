// LoginApplication.java

package com.example;

import java.sql.*;
import java.util.Scanner;

/**
 * Handles user login by authenticating credentials against stored data in the
 * database.
 * This application prompts the user for their username and password, verifies
 * these credentials,
 * and logs the outcome of each login attempt.
 */
public class LoginApplication {

    /**
     * Main method that initiates the application, prompts user input for
     * credentials, and performs authentication.
     * Logs the outcome and informs the user whether the login was successful.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            // Attempts to authenticate the user and logs the outcome.
            if (authenticateUser(username, password)) {
                AppLogger.logInfo("Login successful for user: " + username);
                System.out.println("Login successful!");
            } else {
                AppLogger.logWarning("Login failed for user: " + username);
                System.out.println("Login failed!");
            }
        }
    }

    /**
     * Authenticates user credentials against the database.
     * 
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return true if credentials are correct and authentication is successful,
     *         false otherwise.
     */
    private static boolean authenticateUser(String username, String password) {
        // SQL query to retrieve the stored password for a given username
        String query = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            // Check if a password is retrieved for the username
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                // Verify if the entered password matches the stored password hash
                if (PasswordUtil.checkPass(password, storedPassword)) {
                    return true;
                } else {
                    AppLogger.logInfo("Authentication failed due to incorrect password for user: " + username);
                    return false;
                }
            } else {
                AppLogger.logInfo("Authentication failed due to username not found: " + username);
                return false;
            }
        } catch (SQLException e) {
            // Log any SQL exceptions that occur during the authentication process
            AppLogger.logException("Database error during authentication for user: " + username, e);
            return false;
        }
    }
}