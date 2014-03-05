package com.eguller.mouserecorder.recorder.event;

import org.apache.tools.ant.types.resources.selectors.None;

import java.awt.*;

/**
 * User: eguller
 * Date: 3/4/14
 * Time: 6:57 AM
 */
public class NoneEvent implements Event{
    public static final NoneEvent INSTANCE = new NoneEvent();

    private NoneEvent(){}
    @Override
    public void execute(Robot robot) {

    }
}
