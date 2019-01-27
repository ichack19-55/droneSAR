package com.droneSAR.backend;

public class SARAdmin extends User {

    private static final CampaignStore campaignStore =
        CampaignStore.getInstance();
    private static final int NULL_CAMPAIGN_ID = -1;
    private int createdCampaignId;
    private Campaign campaign;

    public SARAdmin(int userId) {
        super(userId);
    }

    public void createCampaign() {
        // Can only create one campaign:
        if (!this.hasCampaignBeenCreated()) {
            this.campaign = new Campaign(createdCampaignId + "");
            CampaignStore.getInstance().addCampaign(campaign);
        }
    }

    public void deleteCampaign() {
        if (this.hasCampaignBeenCreated()) {
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

    public void addDroneClip(DroneClip clip) {
        campaign.addDroneClip(clip);
    }

    public void removeDroneClip() {
        campaign.removeDroneClip();
    }
}
