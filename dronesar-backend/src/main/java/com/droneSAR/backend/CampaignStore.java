package com.droneSAR.backend;

import java.util.HashSet;
import java.util.Set;

/*
 * Singleton pattern store for Campaigns.
 */
public class CampaignStore {

  private static CampaignStore store = new CampaignStore();

  private Set<Campaign> campaignStore = new HashSet<>();

  public static CampaignStore getInstance() {
    return store;
  }

  public void addCampaign(Campaign campaign) {
    this.campaignStore.add(campaign);
  }

  public void removeCampaign(Campaign campaign) {
    this.campaignStore.remove(campaign);
  }

  public Set<Campaign> getCampaignStore() {
    return campaignStore;
  }
}
