package com.droneSAR.backend;

public class ClipBeingReviewed {

    private static ClipBeingReviewed clip = new ClipBeingReviewed();

    private DroneClip droneClip;
    private String campaignName;

    public static ClipBeingReviewed getInstance() {
        return clip;
    }

    public DroneClip getDroneClip() {
        return droneClip;
    }

    public void setDroneClip(DroneClip droneClip) {
        this.droneClip = droneClip;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }
}
