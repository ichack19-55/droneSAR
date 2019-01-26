package com.droneSAR.backend;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class User {

    private final int userId;
    private HashMap<Integer, Campaign> subscribedCampaigns;

    public User(int userId) {
        this.userId = userId;
        this.subscribedCampaigns = new LinkedHashMap<>();
    }

    public void subscribeTo(Campaign campaign){
        this.subscribedCampaigns.put(campaign.getCampaignId(), campaign);
    }

    public void unsubscribeTo(int id) {
        this.subscribedCampaigns.remove(id);
    }

    public Set<Integer> getSubscribedCampaignIds() {
        return this.subscribedCampaigns.keySet();
    }

    public int getUserId() {
        return this.userId;
    }

    /*
    public void playFootage() {
        // TODO:
    }

    public void pauseFootage() {
        // TODO:
    }

    public void requestNewFootage() {
        // TODO:
    }
    */

    public void flagFootage() {
        //Store.getInstance().putHits(Store.getInstance().getTotalHitCount(), picture, this);

    }

}
