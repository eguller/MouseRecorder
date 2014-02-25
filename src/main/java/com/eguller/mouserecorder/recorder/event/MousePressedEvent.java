package com.eguller.mouserecorder.recorder.event;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created with IntelliJ IDEA.
 * User: eguller
 * Date: 12/4/13
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MousePressedEvent extends MouseButtonEvent{
    public MousePressedEvent(int button) {
        super(button);
    }

    @Override
    public void execute(Robot robot) {
        robot.mousePress(button);
    }
}
