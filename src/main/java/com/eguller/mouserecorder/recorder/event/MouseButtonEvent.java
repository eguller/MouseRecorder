package com.eguller.mouserecorder.recorder.event;

import java.awt.event.InputEvent;

/**
 * Created with IntelliJ IDEA.
 * User: eguller
 * Date: 12/4/13
 * Time: 5:43 PM
 */
public abstract class MouseButtonEvent implements Event{
    private static boolean pressed = false;
    protected  int button;
    public MouseButtonEvent(int button){
        super();
        this.button = button;
    }

    public int getButton(){
        return button;
    }

    public static boolean isAlreadyPressed(){
        return pressed;
    }
}
