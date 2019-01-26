package com.droneSAR.backend;

import java.util.Set;

public class SARAdmin extends User {

    private static final CampaignStore campaignStore = CampaignStore.getInstance();
    private static final int NULL_CAMPAIGN_ID = -1;
    private int createdCampaignId;

    public SARAdmin(int userId) {
        super(userId);
    }

    public void createCampaign(){
        // Can only create one campaign:
        if (!this.hasCampaignBeenCreated()) {
            Campaign campaign = new Campaign();
            CampaignStore.getInstance().addCampaign(campaign);
        } else {
            // Campaign already created!
        }
    }

    public void deleteCampaign() {
        if (this.hasCampaignBeenCreated()) {

            Campaign campaign = campaignStore.getCampaign(createdCampaignId);
            Set<User> crowdReviewers = campaign.getCrowdReviewers();

            // unsubscribe all users to campaign:
            for (User user : crowdReviewers) {
                user.unsubscribeTo(createdCampaignId);
            }

            campaignStore.removeCampaign(campaign);
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
