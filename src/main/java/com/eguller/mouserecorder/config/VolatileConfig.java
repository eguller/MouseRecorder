package com.eguller.mouserecorder.config;

/**
 * User: eguller
 * Date: 3/9/14
 * Time: 10:45 PM
 */
public class VolatileConfig implements Config {
    boolean minimizeOnPlay = false;
    boolean minimizeOnRecord = false;
    double speed = 1.0;
    int loopCount = 1;
    boolean infiniteLoop = false;

    @Override
    public Config activateMinimizeOnPlay() {
        minimizeOnPlay = true;
        return this;
    }

    @Override
    public Config deactivateMinimizeOnPlay() {
        minimizeOnPlay = false;
        return this;
    }

    @Override
    public Config activateMinimizeOnRecord() {
        minimizeOnRecord = true;
        return this;
    }

    @Override
    public Config deactivateMinimizeOnRecord() {
        minimizeOnRecord = false;
        return this;
    }

    @Override
    public Config setSpeed(double speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public boolean getMinimizeOnPlay() {
        return minimizeOnPlay;
    }

    @Override
    public boolean getMinimizeOnRecord() {
        return minimizeOnRecord;
    }

    @Override
    public Config setLoopCount(int loopCount) {
        this.loopCount = loopCount;
        return this;
    }

    @Override
    public int getLoopCount() {
        return loopCount;
    }

    @Override
    public Config activeInfiniteLoop() {
        this.infiniteLoop = true;
        return this;
    }

    @Override
    public Config deactivateInfiniteLoop() {
        this.infiniteLoop = false;
        return this;
    }

    @Override
    public boolean isInfiniteLoop() {
        return infiniteLoop;
    }
}
