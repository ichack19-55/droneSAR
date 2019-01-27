package com.droneSAR.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Campaign {

    private static int id;

    private final int campaignId;
    private final String name;
    private Set<User> crowdReviewers;
    private List<DroneClip> droneClips;

    public Campaign(String name) {
        this.name = name;

        campaignId = id++;
        droneClips = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public Set<User> getCrowdReviewers() {
        return this.crowdReviewers;
    }

    public void addDroneClip(DroneClip clip) {
        droneClips.add(clip);
    }

    public void removeDroneClip(DroneClip clip) {
        droneClips.remove(clip);
    }
}
