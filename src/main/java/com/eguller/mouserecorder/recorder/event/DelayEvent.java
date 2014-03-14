package com.eguller.mouserecorder.recorder.event;

import com.eguller.mouserecorder.config.Config;

import java.awt.*;

/**
 * User: eguller
 * Date: 2/23/14
 * Time: 2:20 AM
 */
public class DelayEvent implements Event {
    long delay;
    Config config;

    public DelayEvent(long delay, Config config) {
        this.delay = delay;
        this.config = config;
    }


    public long getDelay() {
        return delay;
    }

    @Override
    public void execute(Robot robot) {
        long speedDelay = (long) (delay / config.getSpeed());
        if (speedDelay > 0) {
            try {
                Thread.sleep(speedDelay);
            } catch (InterruptedException e) {
                //ignore
            }
        }
    }
}
