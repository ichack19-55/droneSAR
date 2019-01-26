package com.droneSAR.backend;

import java.util.HashMap;

public class SARAdmin extends User {

    private static final int NULL_CAMPAIGN_ID = -1;
    private HashMap<Integer, Campaign> campaigns;
    private int createdCampaignId;

    public SARAdmin(int userId) {
        super(userId);
    }

    public void createCampaign(){
        // Can only create one campaign:
        if (!this.hasCampaignBeenCreated()) {
            Campaign campaign = new Campaign();
            campaigns.put(campaign.getCampaignId(), campaign);
        } else {
            // Campaign already created!
        }
    }

    public void deleteCampaign() {
        if (this.hasCampaignBeenCreated()) {
            CampaignStore.getInstance().removeCampaign(createdCampaignId);
            this.setCreatedCampaignIdToNullId();
        }
    }

    private boolean hasCampaignBeenCreated() {
        return this.createdCampaignId > NULL_CAMPAIGN_ID;
    }

    private void setCreatedCampaignIdToNullId() {
        this.createdCampaignId = this.NULL_CAMPAIGN_ID;
    }
}
