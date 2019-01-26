package com.droneSAR.backend;

//import org.bytedeco.javacv.FFmpegFrameGrabber;
//import org.bytedeco.javacv.FrameGrabber;

//import javax.imageio.ImageIO;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class DroneClip {

    private final File video;

    // Time between review frames in milliseconds
    private int timeBetweenReviewFrames;

    // Stores how many times this clip needs to be reviewed vs how many times it has been reviewed
    private int reviewsNeeded;
    private int reviewsMade;

    // Store the times at which this clip has been flagged
    private Set<Integer> flags;

    // Will eventually store the flightpath relating to this clip
    //private Flightpath flightpath;

    public DroneClip(File video, int reviewsNeeded) {
        this.video = video;
        this.reviewsNeeded = reviewsNeeded;

        reviewsMade = 0;
        flags = new HashSet<>();
    }

    public void setReviewsNeeded(int reviewsNeeded) {
        this.reviewsNeeded = reviewsNeeded;
    }

    public int getReviewsNeeded() {
        return reviewsNeeded;
    }

    public void addReview() {

    }

    public void extractFrame(int millis) {
        /*FFmpegFrameGrabber g = new FFmpegFrameGrabber(video);
        try {
            g.start();

            for (int i = 0; i < 50; i++) {
                ImageIO.write(g.grab().getBufferedImage(), "png", new File(
                    "frame-dump/video-frame-" + System.currentTimeMillis() + ".png"));
            }

            g.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }*/
    }

    private void splitFootageIntoSegments(/*input file*/) {
        // getFootage(/*input file*/) - function in Footage to return list of small footages from input.
        // then add to store
    }
}
