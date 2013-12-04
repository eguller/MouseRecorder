package com.eguller.mouserecorder.recorder.event;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: eguller
 * Date: 12/4/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class MouseReleaseEvent extends MouseButtonEvent{

    public MouseReleaseEvent(int button) {
        super(button);
    }

    @Override
    public void execute(Robot robot) {
        robot.mouseRelease(button);
    }
}
