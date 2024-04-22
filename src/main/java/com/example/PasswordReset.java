// PasswordReset.java

package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Handles the process of resetting a user's password by verifying their
 * identity through a security question.
 */
public class PasswordReset {

    /**
     * Main method to initiate the password reset process. Prompts the user for
     * their username,
     * and upon successful identity verification, allows setting a new password.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your username:");
            String username = scanner.nextLine().trim();

            // Verify user identity through security question
            if (!verifyIdentity(username, scanner)) {
                System.out.println("Security question verification failed.");
                return;
            }

            System.out.println("Enter your new password:");
            String newPassword = scanner.nextLine().trim();
            // Reset the password if identity is confirmed
            if (resetPassword(username, newPassword)) {
                System.out.println("Password has been successfully reset.");
            } else {
                System.out.println("Password reset failed.");
            }
        } finally {
            scanner.close(); // Ensure scanner is closed to avoid resource leaks
        }
    }

    /**
     * Verifies the identity of the user by asking them to answer their security
     * question.
     * 
     * @param username The username of the user attempting to reset their password.
     * @param scanner  The Scanner object for reading user input.
     * @return true if the user correctly answers their security question, false
     *         otherwise.
     */
    private static boolean verifyIdentity(String username, Scanner scanner) {
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT security_question, security_answer FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            // Check if security question and answer exist for the user
            if (resultSet.next() && resultSet.getString("security_question") != null
                    && resultSet.getString("security_answer") != null) {
                System.out.println("Answer the security question to verify your identity:");
                System.out.println(resultSet.getString("security_question"));
                String answer = scanner.nextLine().trim();

                // Return false if the answer field is left empty
                if (answer.isEmpty()) {
                    System.out.println("Answer cannot be empty.");
                    return false;
                }

                // Check if the provided answer matches the stored hashed answer
                return PasswordUtil.checkPass(answer, resultSet.getString("security_answer"));
            } else {
                System.out.println("No security question or answer set for this user.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error verifying user identity: " + e.getMessage());
            return false;
        }
    }

    /**
     * Resets the user's password in the database.
     * 
     * @param username    The username of the user whose password is being reset.
     * @param newPassword The new password that will replace the old one.
     * @return true if the password is successfully updated, false otherwise.
     */
    private static boolean resetPassword(String username, String newPassword) {
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE users SET password = ? WHERE username = ?")) {
            stmt.setString(1, PasswordUtil.hashPassword(newPassword)); // Hash the new password for security
            stmt.setString(2, username);

            // Execute the update and check if it was successful
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error resetting password: " + e.getMessage());
            return false;
        }
    }
}