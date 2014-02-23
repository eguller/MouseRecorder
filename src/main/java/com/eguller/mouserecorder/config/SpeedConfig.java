package com.eguller.mouserecorder.config;

/**
 * User: eguller
 * Date: 2/23/14
 * Time: 2:28 AM
 */
public class SpeedConfig {
    double speedX = 1.0;
    public SpeedConfig(double speedX){
        speedX = speedX;
    }
    public SpeedConfig(){}
    public double getSpeedX(){
        return speedX;
    }
}
