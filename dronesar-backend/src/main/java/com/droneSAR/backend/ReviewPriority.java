package com.droneSAR.backend;

public enum ReviewPriority {
    LOW(10),
    MEDIUM(5),
    HIGH(2),
    CRITICAL(1);

    // Number of seconds between review frames
    private final int secondsBetweenFrames;

    ReviewPriority(int secondsBetweenFrames) {
        this.secondsBetweenFrames = secondsBetweenFrames;
    }

    public int getSecondsBetweenFrames() {
        return secondsBetweenFrames;
    }
}
