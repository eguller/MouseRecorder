package com.eguller.mouserecorder.recorder.event;

import java.awt.*;

/**
 * User: eguller
 * Date: 3/4/14
 * Time: 6:57 AM
 */
public class NoneEvent implements Event{
    public static final NoneEvent INSTANCE = new NoneEvent();
    @Override
    public void execute(Robot robot) {

    }
}
