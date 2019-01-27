package com.droneSAR.authentication;

import java.io.Serializable;

/**
 * Simple interface for authentication and authorization checks.
 */
public interface AccessControl extends Serializable {
    boolean createUser(String username, String password);
    boolean signIn(String username, String password);
    boolean isUserSignedIn();
    boolean isUserInRole(String role);
    String getPrincipalName();
}
