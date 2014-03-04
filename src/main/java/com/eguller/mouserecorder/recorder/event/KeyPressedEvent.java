package com.eguller.mouserecorder.recorder.event;

import com.eguller.mouserecorder.format.KeyWrapper;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: eguller
 * Date: 12/4/13
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class KeyPressedEvent extends KeyEvent{
    public KeyPressedEvent(int key) {
        super(key);
    }

    public KeyPressedEvent(String key){
        super(KeyWrapper.keyToCode(key));
    }

    @Override
    public void execute(Robot robot) {
        robot.keyPress(key);
    }
}
