package com.droneSAR.authentication;

public class AccessControlFactory {
    private static final AccessControl userAccessControl = new UserAccessControl();

    private AccessControlFactory() {
    }

    public static AccessControl getUAC() {
        return userAccessControl;
    }
}
