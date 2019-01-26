package com.droneSAR.backend;

import java.util.HashMap;
import java.util.Map;

public class Store {

  //store: hits - user, hits - footage, user - footage

  Map<Integer, User> hitsUser;
  Map<Integer, Footage> hitsFootage;
  Map<User, Footage> userFootage;

  public Store(){
    hitsUser = new HashMap<>();
    hitsFootage = new HashMap<>();
    userFootage = new HashMap<>();
  }

  public User getUserFromHit(Integer hit){
    return hitsUser.get(hit);
  }

  public void putUserHit(Integer hit, User user){
    hitsUser.put(hit, user);
  }

  public Footage getFootageFromHit(Integer hit){
    return hitsFootage.get(hit);
  }

  public void putHitsFootage(Integer hit, Footage footage){
    hitsFootage.put(hit, footage);
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
