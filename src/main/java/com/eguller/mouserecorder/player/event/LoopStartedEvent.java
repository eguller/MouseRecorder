package com.eguller.mouserecorder.player.event;

/**
 * User: eguller
 * Date: 3/11/14
 * Time: 7:46 AM
 */
public class LoopStartedEvent {
    boolean isInfiniteLoop;
    int currentLoop;
    int totalLoop;

    public LoopStartedEvent(boolean isInfiniteLoop, int currentLoop, int totalLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        this.currentLoop = currentLoop;
        this.totalLoop = totalLoop;
    }

    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    public int getCurrentLoop() {
        return currentLoop;
    }

    public int getTotalLoop() {
        return totalLoop;
    }
}
