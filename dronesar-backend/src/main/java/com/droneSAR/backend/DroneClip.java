package com.droneSAR.backend;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import static org.bytedeco.javacv.Java2DFrameUtils.deepCopy;

public class DroneClip {

    private static final int SECONDS_TO_MICRO = 1000 * 1000;

    // The video clip referenced by this DropClip
    private final File video;

    // How many times this clip needs to be reviewed, the time between reviews, and
    // how many times it has been reviewed
    private ReviewPriority priority;

    // Length of the clip in seconds
    private final int length;

    // How much of this current clip has been reviewed already, in seconds
    private int reviewedUpTo = 0;

    // The user currently reviewing this clip
    private User reviewer;

    // Whether this clip is already being reviewed by someone
    private final AtomicBoolean isBeingReviewed = new AtomicBoolean(false);

    // Store the times at which this clip has been flagged (in seconds)
    private Set<Integer> flags = new HashSet<>();

    // Will eventually store the flightpath relating to this clip
    //private Flightpath flightpath;

    public DroneClip(File video, ReviewPriority priority) {
        this.video = video;
        this.priority = priority;

        // Calculate how long the video is
        int trySetLength = -1;
        try (FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(video)) {
            frameGrabber.start();
            trySetLength = (int) (frameGrabber.getLengthInTime() / SECONDS_TO_MICRO);
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } finally {
            length = trySetLength;
        }
    }

    public void setPriority(ReviewPriority priority) {
        this.priority = priority;
    }

    public boolean canReview() {
        return !isBeingReviewed.get();
    }

    public boolean startReviewing(User user) {
        boolean canReview = isBeingReviewed.compareAndSet(false, true);
        if (canReview) {
            reviewer = user;
            return true;
        }
        return false;
    }

    public void finishReviewing(User user) {
        if (user.equals(reviewer)) {
            isBeingReviewed.set(false);
        }
    }

    public String getNextFrameToReviewFile(User user) {
        if (!user.equals(reviewer)) {
            return null;
        }

        reviewedUpTo = reviewedUpTo + priority.getSecondsBetweenFrames();
        return getFrameAtTimestamp(reviewedUpTo);
    }

    // Helper to get frame at x seconds into clip and write to temp file, returning filepath
    private String getFrameAtTimestamp(int seconds) {
        try (FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(video)) {
            frameGrabber.start();
            int micro = seconds * SECONDS_TO_MICRO;
            if (micro > frameGrabber.getLengthInTime()) {
                return null;
            }
            frameGrabber.setTimestamp(micro);

            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage bufferedImage = deepCopy(converter.convert(frameGrabber.grabImage()));

            // Write frame grab to temp file
            File img = File.createTempFile("frame_at_" + reviewedUpTo + "s", ".jpg");
            ImageIO.write(bufferedImage, "JPG", img);
            return img.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
