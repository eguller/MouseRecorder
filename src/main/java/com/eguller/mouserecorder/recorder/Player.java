package com.eguller.mouserecorder.recorder;

import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Observable;

/**
 * User: eguller
 * Date: 11/24/13
 * Time: 5:20 PM
 *
 * Replays given set of recorded mouse and keyboard actions
 */
public class Player extends Observable implements Runnable{
    Robot robot;
    List<NativeInputEvent> eventList;
    public Player(List<NativeInputEvent> eventList) throws AWTException {
        this.eventList = eventList;
        robot = new Robot();
    }

    @Override
    public void run() {
        long previousEventTime = -1;
         for(NativeInputEvent event : eventList){
             //delay between events. This part will be parametrized to replay
             //events slower or faster.
             if(previousEventTime > 0 && event.getWhen() - previousEventTime > 0){
                 robot.delay( (int)(event.getWhen() - previousEventTime) );
             }
             previousEventTime = event.getWhen();

             //replay mouse and keyboard events
             //right click missing
             //dragging is missing
             if(event instanceof NativeKeyEvent){
                 if(event.getID() == NativeKeyEvent.NATIVE_KEY_PRESSED){
                    robot.keyPress(((NativeKeyEvent) event).getKeyCode());
                 }
                 else if(event.getID() == NativeKeyEvent.NATIVE_KEY_RELEASED){
                     robot.keyRelease(((NativeKeyEvent) event).getKeyCode());
                 }
             }
             else if(event instanceof NativeMouseEvent){
                 //InputEvent
                 if(event.getID() == NativeMouseEvent.NATIVE_MOUSE_PRESSED){
                     robot.mousePress(InputEvent.BUTTON1_MASK);
                 }
                 else if(event.getID() == NativeMouseEvent.NATIVE_MOUSE_RELEASED){
                     robot.mouseRelease(InputEvent.BUTTON1_MASK);
                 }
                 else if(event.getID() == NativeMouseEvent.NATIVE_MOUSE_MOVED){
                     robot.mouseMove(((NativeMouseEvent) event).getX(), ((NativeMouseEvent) event).getY());
                 }
                 else{
                     System.err.println("Not handled event type: " + event.toString());
                 }

             }
         }
        setChanged();
        notifyObservers();
    }
}
