package com.droneSAR.backend;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CampaignStore {

  // Singleton pattern store for Campaigns

  private static CampaignStore store;
  private static HashMap<Integer, Campaign>  campaignStore;

  private CampaignStore() {
    this.campaignStore = new LinkedHashMap<>();
  }

  public static CampaignStore getInstance() {
    if (store == null) {
      store = new CampaignStore();
    }
    return store;
  }

  public void addCampaign(Campaign campaign) {
    this.campaignStore.put(campaign.getCampaignId(), campaign);
  }

  public Campaign getCampaign(int id) {
    return this.campaignStore.get(id);
  }

  public void removeCampaign(Campaign campaign) {
    this.campaignStore.remove(campaign.getCampaignId());
  }

  public void removeCampaign(Integer id) {
    this.campaignStore.remove(id);
  }
}
