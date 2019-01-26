package com.droneSAR.backend;

public class SARAdmin extends User {

    public SARAdmin(int userId) {
        super(userId);
    }

    public void markHitAsDone(Integer hit){
        Store.getInstance().removeHit(hit);
    }


}
