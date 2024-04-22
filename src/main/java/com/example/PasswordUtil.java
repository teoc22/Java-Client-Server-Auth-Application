// PasswordUtil.java

package com.example;

import org.mindrot.jbcrypt.BCrypt;

/**
 * PasswordUtil provides utility methods for hashing passwords and verifying
 * passwords against hashes,
 * using the BCrypt strong hashing function.
 */
public class PasswordUtil {

    /**
     * Hashes a plain text password using BCrypt's hashing algorithm.
     * 
     * @param plainTextPassword The plain text password to hash.
     * @return A hashed version of the plain text password.
     */
    public static String hashPassword(String plainTextPassword) {
        // Generate a salt and hash the password. BCrypt.gensalt() by default uses a
        // work factor of 10.
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * Checks a plain text password against a hashed password to see if they match.
     * 
     * @param plainPassword  The plain text password to verify.
     * @param hashedPassword The hashed password to compare against.
     * @return true if the plain password matches the hashed password, false
     *         otherwise.
     */
    public static boolean checkPass(String plainPassword, String hashedPassword) {
        // Use BCrypt's checkpw method to see if the hashed password matches the plain
        // password after hashing.
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
