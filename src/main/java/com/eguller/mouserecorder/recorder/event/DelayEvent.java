package com.eguller.mouserecorder.recorder.event;

import com.eguller.mouserecorder.config.SpeedConfig;

import java.awt.*;

/**
 * User: eguller
 * Date: 2/23/14
 * Time: 2:20 AM
 */
public class DelayEvent implements Event{
    long delay;
    SpeedConfig speedConfig = new SpeedConfig();
    public DelayEvent(long delay){
        this.delay = delay;
    }

    public void setSpeedConfig(SpeedConfig speedConfig){
        this.speedConfig = speedConfig;
    }


    @Override
    public void execute(Robot robot) {
        long speedDelay = (long)(delay * speedConfig.getSpeedX());
        if(speedDelay > 0){
            try {
                Thread.sleep(speedDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
