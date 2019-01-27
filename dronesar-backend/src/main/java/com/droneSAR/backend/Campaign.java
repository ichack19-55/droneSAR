package com.droneSAR.backend;

public class Campaign {

    private static int id;

    private final int campaignId;
    private final String name;
    public DroneClip droneClip;

    public Campaign(String name) {
        this.name = name;
        campaignId = id++;
    }

    public String getName() {
        return name;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void addDroneClip(DroneClip clip) {
        droneClip = clip;
    }

    public void removeDroneClip() {
        droneClip = null;
    }
}
