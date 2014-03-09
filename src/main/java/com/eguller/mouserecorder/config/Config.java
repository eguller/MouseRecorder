package com.eguller.mouserecorder.config;

/**
 * User: eguller
 * Date: 3/8/14
 * Time: 9:43 AM
 */
public interface Config {
    public Config activateMinimizeOnPlay();

    public Config deactivateMinimizeOnPlay();

    public Config activateMinimizeOnRecord();

    public Config deactivateMinimizeOnRecord();

    public Config setSpeed(double speed);

    public double getSpeed();

    public boolean getMinimizeOnPlay();

    public boolean getMinimizeOnRecord();

    public Config setLoopCount(int loopCount);

    public int getLoopCount();

    public Config activeInfiniteLoop();

    public Config deactivateInfiniteLoop();

    public boolean isInfiniteLoop();
}
