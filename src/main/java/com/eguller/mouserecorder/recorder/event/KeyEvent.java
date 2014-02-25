package com.eguller.mouserecorder.recorder.event;

import com.eguller.mouserecorder.util.OS;

/**
 * Created with IntelliJ IDEA.
 * User: eguller
 * Date: 12/4/13
 * Time: 5:48 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class KeyEvent implements Event{
    protected int key;
    public KeyEvent(int key){
        super();
        this.key = key;
        convertMacCommandKey();
    }

    public int getKey(){
        return key;
    }

    public void convertMacCommandKey(){
        if(OS.isMacOSX()){
            //mac native command key code
            if(this.key == 16){
                this.key = java.awt.event.KeyEvent.VK_META;
            }
        }
    }
}
