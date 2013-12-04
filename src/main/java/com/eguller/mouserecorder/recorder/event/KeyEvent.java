package com.eguller.mouserecorder.recorder.event;

/**
 * Created with IntelliJ IDEA.
 * User: eguller
 * Date: 12/4/13
 * Time: 5:48 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class KeyEvent extends Event{
    protected int key;
    public KeyEvent(int key){
        super();
        this.key = key;
    }
}
