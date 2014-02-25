package com.eguller.mouserecorder.recorder.event;

import java.awt.event.InputEvent;

/**
 * Created with IntelliJ IDEA.
 * User: eguller
 * Date: 12/4/13
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MouseButtonEvent implements Event{
    private static boolean pressed = false;
    protected  int button;
    public MouseButtonEvent(int button){
        super();
        this.button = getMaskForButton(button);
    }

    public int getButton(){
        return button;
    }

    private static int getMaskForButton(int button){
        int ret = InputEvent.BUTTON1_MASK;
        switch (button){
            case 1:
                ret = InputEvent.BUTTON1_MASK;
                break;
            case 2:
                ret = InputEvent.BUTTON3_MASK;
                break;
            case 3:
                ret = InputEvent.BUTTON2_MASK;
                break;
        }
        return ret;
    }

    public static boolean isAlreadyPressed(){
        return pressed;
    }
}
