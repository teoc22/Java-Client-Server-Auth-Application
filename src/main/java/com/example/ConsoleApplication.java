//ConsoleApplication.java

package com.example;

import java.util.Scanner;

/**
 * A simple console application to handle login and logout functionality.
 * This application continuously accepts commands from the user to log in, log
 * out, or exit the application.
 * It uses a boolean flag to track the authentication state of the user.
 */
public class ConsoleApplication {
    private static boolean isAuthenticated = false; // Tracks whether the user is logged in or not.

    /**
     * The main method that drives the application, listening for commands in a
     * loop.
     * It uses a scanner to read console input and processes commands until the user
     * decides to exit.
     * 
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter command (login, logout, exit):");
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "login":
                        if (!isAuthenticated) {
                            isAuthenticated = true;
                            System.out.println("You are now logged in.");
                        } else {
                            System.out.println("You are already logged in.");
                        }
                        break;
                    case "logout":
                        if (isAuthenticated) {
                            isAuthenticated = false;
                            System.out.println("You have been logged out.");
                        } else {
                            System.out.println("You are not logged in.");
                        }
                        break;
                    case "exit":
                        System.out.println("Exiting application...");
                        return; // Exits the application by breaking out of the loop.
                    default:
                        if (!isAuthenticated) {
                            System.out.println("You are not logged in. Please log in first.");
                        } else {
                            System.out.println("Command received: " + command);
                            // Additional commands can be handled here if necessary.
                        }
                        break;
                }
            }
        }
    }
}