package com.eguller.mouserecorder.recorder.event;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: eguller
 * Date: 12/4/13
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Event {
    long when;
    public Event(){
        when = System.currentTimeMillis();
    }
    public abstract void execute(Robot robot);

    public long getWhen(){
        return when;
    }

}
