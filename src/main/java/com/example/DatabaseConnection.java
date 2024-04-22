// DatabaseConnection.java

package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseConnection class handles establishing connections to the
 * database.
 */
public class DatabaseConnection {
    // Database URL, including the protocol, server address, and specific database
    // name.
    private static final String URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11700968";

    // Username for accessing the database.
    private static final String USER = "sql11700968";

    // Corresponding password for the database user.
    private static final String PASSWORD = "FJM4f55IZB";

    /**
     * Attempts to establish a connection to the database using the defined
     * credentials and URL.
     * 
     * @return A connection to the database.
     * @throws RuntimeException if the connection cannot be established, throws a
     *                          runtime exception with error details.
     */
    public static Connection getConnection() {
        try {
            // Attempt to get a connection to the database using the DriverManager
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Throw a runtime exception if database connection fails
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
