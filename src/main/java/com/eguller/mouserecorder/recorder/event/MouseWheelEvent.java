package com.eguller.mouserecorder.recorder.event;

import java.awt.*;

/**
 * User: eguller
 * Date: 2/25/14
 * Time: 7:43 AM
 */
public class MouseWheelEvent implements Event{
    int scrollAmount = 0;
    public MouseWheelEvent(int scrollAmount){
        this.scrollAmount = scrollAmount;
    }

    public int getScrollAmount() {
        return scrollAmount;
    }

    @Override
    public void execute(Robot robot) {
        robot.mouseWheel(scrollAmount);
    }
}
