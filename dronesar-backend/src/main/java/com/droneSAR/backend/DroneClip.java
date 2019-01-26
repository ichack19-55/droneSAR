package com.droneSAR.backend;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.bytedeco.javacv.Java2DFrameUtils.deepCopy;

public class DroneClip {

    private static final int SECONDS_TO_MICRO = 1000 * 1000;

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

    // CURRENTLY WRITES TOI FILE AND OPENS FILE MACOS ONLY
    // TODO: can only process MPEG footage currently
    // TODO: what now boss? what should this function do?
    public void getFrameAtTimestamp(int seconds) {
        try (FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(video)) {
            frameGrabber.start();
            int micro = seconds * SECONDS_TO_MICRO;
            if (micro > frameGrabber.getLengthInTime()) {
                return;
            }
            frameGrabber.setTimestamp(micro);

            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage bufferedImage = deepCopy(converter.convert(frameGrabber.grabImage()));

            // Write frame grab to temp file
            File img = File.createTempFile("insta", ".jpg");
            ImageIO.write(bufferedImage, "JPG", img);

            ProcessBuilder pb = new ProcessBuilder("open", img.getAbsolutePath());
            pb.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
