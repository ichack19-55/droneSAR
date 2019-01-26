package com.droneSAR.backend;

import java.util.List;
import java.util.Set;

public class Campaign {

    private static int id;
    private final int campaignId;
    private SARAdmin owner;
    private Set<User> crowdReviewers;
    private List<DroneClip> droneClips;

    public Campaign(){
        this.campaignId = id;
        id++;
    }

    public int getCampaignId(){
        return campaignId;
    }

    public Set<User> getCrowdReviewers() {
        return this.crowdReviewers;
    }
}
