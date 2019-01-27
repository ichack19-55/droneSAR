package com.droneSAR.backend;

import java.util.HashMap;
import java.util.LinkedHashMap;

/*
 * Singleton pattern store for Campaigns.
 */
public class CampaignStore {

  private static CampaignStore store = new CampaignStore();
  private HashMap<Integer, Campaign>  campaignStore = new LinkedHashMap<>();

  public static CampaignStore getInstance() {
    return store;
  }

  public void addCampaign(Campaign campaign) {
    this.campaignStore.put(campaign.getCampaignId(), campaign);
  }

  public Campaign getCampaign(int id) {
    return this.campaignStore.get(id);
  }

  public void removeCampaign(Integer id) {
    this.campaignStore.remove(id);
  }

  public void removeCampaign(Campaign campaign) {
    this.campaignStore.remove(campaign.getCampaignId());
  }
}
