package com.droneSAR.backend;

import org.junit.Test;

public class CompileTest {

    @Test
    public void testCampaignCompiles() {
        Campaign c = new Campaign();
    }

    @Test
    public void testUserCompiles(){
        User u = new User(123);
    }

    @Test
    public void testStoreCompiles(){
        Store s = Store.getInstance();
    }

    @Test
    public void testSARAdminCompiles(){
        SARAdmin a = new SARAdmin(123);
    }
}
