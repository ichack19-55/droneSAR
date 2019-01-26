package com.droneSAR.backend;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class CompileTest {

    @Test
    public void testCampaignCompiles() {
        Campaign c = new Campaign();
    }

    @Test
    public void testUserCompiles() {
        User u = new User(123);
    }

    @Test
    public void testSARAdminCompiles() {
        SARAdmin a = new SARAdmin(123);
    }

    @Test
    public void canGrabFrameFromVideo() {
        DroneClip dc = new DroneClip(new File("/Users/Matt/Desktop/test.mp4"), ReviewPriority.HIGH);
        User u = new User(1);
        dc.startReviewing(u);

        for (int i = 0; i < 10; i++) {
            /*String path = dc.getNextFrameToReviewFile(u);
            ProcessBuilder pb = new ProcessBuilder("open", path);
            try {
                pb.start();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dc.finishReviewing(u);

    }
}
