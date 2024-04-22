//AppLogger.java

package com.example;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Utility class for logging various levels of messages and exceptions to a
 * file.
 * Uses Java's built-in logging framework to log informational, warning, and
 * severe messages.
 */
public class AppLogger {
    private static final Logger logger = Logger.getLogger(AppLogger.class.getName());
    private static FileHandler fileHandler; // Handler for directing log messages to a file.

    static {
        try {
            // Initialize FileHandler to append logs to "application.log".
            fileHandler = new FileHandler("application.log", true);
            logger.addHandler(fileHandler);

            // Use SimpleFormatter to format logs in a readable form.
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (Exception e) {
            // Log any exceptions that occur during the setup of logging.
            logger.log(Level.SEVERE, "Failed to initialize log file handler", e);
        }
    }

    /**
     * Logs an informational message.
     *
     * @param message The message to log, typically describing a normal but
     *                significant process.
     */
    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    /**
     * Logs a severe error message.
     *
     * @param message The error message to log, typically describing a serious
     *                failure.
     */
    public static void logSevere(String message) {
        logger.log(Level.SEVERE, message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The warning message to log, typically describing a potential
     *                issue or minor failure.
     */
    public static void logWarning(String message) {
        logger.log(Level.WARNING, message);
    }

    /**
     * Logs an exception along with a custom message.
     *
     * @param message The message describing the error context.
     * @param thrown  The exception to log.
     */
    public static void logException(String message, Throwable thrown) {
        logger.log(Level.SEVERE, message, thrown);
    }

    /**
     * Ensures that the FileHandler is properly closed to release resources.
     */
    public static void closeLogger() {
        fileHandler.close();
    }
}