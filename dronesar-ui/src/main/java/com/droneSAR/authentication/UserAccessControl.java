package com.droneSAR.authentication;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.*;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Optional;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class UserAccessControl implements AccessControl {

    private static final String UAC_CSV_FILENAME = Paths.get(".").toAbsolutePath() + "/src/main/webapp/login/login.csv";
    private static final String CSV_DELIMITER = ",";
    private static final int HASH_ITERATIONS = 3;
    private static final byte[] SALT = {1, 2, 3, 4};

    private static String fakeHashLol(String string) {
        return string;
    }

    private static String pbkdSha512Hash(String string) {
        try {
            char[] chars = string.toCharArray();
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(chars, SALT, HASH_ITERATIONS, chars.length);
            SecretKey key = skf.generateSecret(spec);
            return Arrays.toString(key.getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<String> getPasswordHashFor(String username) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(UAC_CSV_FILENAME))) {
            while ((line = br.readLine()) != null) {
                String[] accountDetails = line.split(CSV_DELIMITER);
                if (username.equals(accountDetails[0])) {
                    return Optional.of(accountDetails[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override public boolean createUser(String username, String password) {
        if (getPasswordHashFor(username).isPresent()) {
            return false;
        }

        try (BufferedWriter br = new BufferedWriter(new FileWriter(UAC_CSV_FILENAME, true))) {
            br.write(username + CSV_DELIMITER + fakeHashLol(password) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override public boolean signIn(String username, String password) {
        if (username == null || username.isEmpty()) {
            return false;
        }

        if (getPasswordHashFor(username).orElse("")
            .equals(fakeHashLol(password))) {
            CurrentUser.set(username);
            return true;
        }
        return false;
    }

    @Override public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    // TODO: review this
    @Override public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getPrincipalName().equals("admin");
        }

        // All users are in all non-admin roles
        return true;
    }

    @Override public String getPrincipalName() {
        return CurrentUser.get();
    }

}
