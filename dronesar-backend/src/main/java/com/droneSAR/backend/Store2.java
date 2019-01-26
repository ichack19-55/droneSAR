package com.droneSAR.backend;

import java.util.Map;

public class Store2 {

    // store: hits - user, hits - footage, user - footage
    private static Store2 store2;
    private Map<Integer, DataBlock> dataIdToDataBlock;
    private Integer totalHitCount;

    private Store2(){
        totalHitCount = 0;
    }

    public static Store2 getInstance() {
        if (store2 == null) {
            store2 = new Store2();
        }
        return store2;
    }

    public Integer getTotalHitCount() {
        return this.totalHitCount;
    }

    public Picture getPicture() {
        // TODO
        return null;
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
