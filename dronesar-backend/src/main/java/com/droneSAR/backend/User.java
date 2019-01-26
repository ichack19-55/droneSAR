package com.droneSAR.backend;


public class User {

    private final int userId;
    private Footage footage;

    public User(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void viewFootage() {
        Store.getInstance().putUserFootage(this, footage);
        // TODO:
    }

    public void playFootage() {
        // TODO:
    }

    public void pauseFootage() {
        // TODO:
    }

    public void requestNewFootage() {
        // TODO:
    }

    public void flagFootage() {
        Store.getInstance().putHits(Store.getInstance().hitCount, footage, this);

    }


}
