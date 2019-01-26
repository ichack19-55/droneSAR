package com.droneSAR.backend;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class User {

    private final int userId;
    HashMap<Integer, Campaign> subscribedCampaigns;

    public User(int userId) {
        this.userId = userId;
        this.subscribedCampaigns = new LinkedHashMap<>();
    }

    public void subscribeTo(Campaign campaign){
        this.subscribedCampaigns.put(campaign.getCampaignId(), campaign);
    }

    public int getUserId() {
        return this.userId;
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
        //Store.getInstance().putHits(Store.getInstance().getTotalHitCount(), picture, this);

    }

}
