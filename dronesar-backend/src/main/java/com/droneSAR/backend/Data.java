package com.droneSAR.backend;

public class Data {

    private final Picture picture;
    private final GPS gps;
    private int views;

    public Data(Picture picture, GPS gps) {
        this.picture = picture;
        this.gps = gps;
    }

}
