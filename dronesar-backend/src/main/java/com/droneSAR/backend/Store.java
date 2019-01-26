package com.droneSAR.backend;

import java.util.HashMap;
import java.util.Map;

public class Store {

  // store: hits - user, hits - footage, user - footage
  private static Store store;
  private Map<Integer, User> hitsUser;
  private Map<Integer, Picture> hitsPicture;
  private Map<User, Picture> userPicture;
  private Integer totalHitCount;

  private Store(){
    hitsUser = new HashMap<>();
    hitsPicture = new HashMap<>();
    userPicture = new HashMap<>();
    totalHitCount = 0;
  }

  public static Store getInstance() {
    if (store == null) {
      store = new Store();
    }
    return store;
  }

  public Integer getTotalHitCount() {
    return this.totalHitCount;
  }

  public User getUserFromHit(Integer hit){
    return hitsUser.get(hit);
  }


  public Picture getFootageFromHit(Integer hit){
    return hitsPicture.get(hit);
  }

  public void putHits(Integer hit, Picture footage, User user){
    hitsPicture.put(hit, footage);
    hitsUser.put(hit, user);
    totalHitCount ++;
  }

  public Picture getFootageFromUser(User user){
    return userPicture.get(user);
  }

  public void putUserFootage(User user, Picture footage){
    userPicture.put(user, footage);
  }

  public Map<Integer, User> getHitsUser(){
    return hitsUser;
  }

  public Map<Integer, Picture> getHitsFootage() {
    return hitsPicture;
  }

  public Map<User, Picture> getUserFootage() {
    return userPicture;
  }

  public void removeHit(Integer hit){
    hitsUser.remove(hit);
    hitsPicture.remove(hit);
  }
}
