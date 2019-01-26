package com.droneSAR.backend;

import java.io.File;

public class Drone {

  private final File video;

  public Drone(File video){
      this.video = video;
  }

  private void splitFootageIntoSegments(/*input file*/){
    // getFootage(/*input file*/) - function in Footage to return list of small footages from input.
    // then add to store
  }
}
