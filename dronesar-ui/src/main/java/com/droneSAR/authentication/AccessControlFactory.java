package com.droneSAR.authentication;

public class AccessControlFactory {
    private static final AccessControlFactory INSTANCE = new AccessControlFactory();

    private final AccessControl adminAccessControl = new AdminAccessControl();
    private final AccessControl crowdAccessControl = new CrowdAccessControl();

    private AccessControlFactory() {
    }

    public static AccessControlFactory anAccessControl() {
        return INSTANCE;
    }

    public AccessControl forAdmins() {
        return adminAccessControl;
    }

    public AccessControl forCrowds() {
        return crowdAccessControl;
    }
}
