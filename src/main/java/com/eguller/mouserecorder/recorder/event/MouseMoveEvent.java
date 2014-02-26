package com.eguller.mouserecorder.recorder.event;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: eguller
 * Date: 12/4/13
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class MouseMoveEvent implements Event{
    private int x;
    private int y;
    public MouseMoveEvent(int x, int y){
        super();
        this.x = x;
        this.y = y;
    }
    @Override
    public void execute(Robot robot) {
        robot.mouseMove(x,y);
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
