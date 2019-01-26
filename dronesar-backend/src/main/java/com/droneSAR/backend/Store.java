package com.droneSAR.backend;

import java.util.HashMap;
import java.util.Map;

public class Store {

  //store: hits - user, hits - footage, user - footage
  private static Store store;
  Map<Integer, User> hitsUser;
  Map<Integer, Footage> hitsFootage;
  Map<User, Footage> userFootage;
  Integer hitId;

  private Store(){
    hitsUser = new HashMap<>();
    hitsFootage = new HashMap<>();
    userFootage = new HashMap<>();
    hitId = 0;
  }

  public static Store getInstance() {
    if (store == null) {
      store = new Store();
    }
    return store;
  }

  public User getUserFromHit(Integer hit){
    return hitsUser.get(hit);
  }


  public Footage getFootageFromHit(Integer hit){
    return hitsFootage.get(hit);
  }

  public void putHits(Integer hit, Footage footage, User user){
    hitsFootage.put(hit, footage);
    hitsUser.put(hit, user);
    hitId++;
  }

  public Footage getFootageFromUser(User user){
    return userFootage.get(user);
  }

  public void putUserFootage(User user, Footage footage){
    userFootage.put(user, footage);
  }

  public Map<Integer, User> getHitsUser(){
    return hitsUser;
  }

  public Map<Integer, Footage> getHitsFootage() {
    return hitsFootage;
  }

  public Map<User, Footage> getUserFootage() {
    return userFootage;
  }
}
