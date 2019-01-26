package com.droneSAR.backend;

public class Data {

    private final Footage footage;
    private final GPS gps;
    private int views;

    public Data(Footage footage, GPS gps) {
        this.footage = footage;
        this.gps = gps;
    }
}
