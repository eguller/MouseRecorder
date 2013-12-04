package com.eguller.mouserecorder.recorder;

import com.eguller.mouserecorder.recorder.event.Event;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Observable;

/**
 * User: eguller
 * Date: 11/24/13
 * Time: 5:20 PM
 * <p/>
 * Replays given set of recorded mouse and keyboard actions
 */
public class Player extends Observable implements Runnable {
    Robot robot;
    List<Event> eventList;

    public Player(List<Event> eventList) throws AWTException {
        this.eventList = eventList;
        robot = new Robot();
    }

    @Override
    public void run() {
        long previousEventTime = -1;
        for (Event event : eventList) {
            //delay between events. This part will be parametrized to replay
            //events slower or faster.
            if (previousEventTime > 0 && event.getWhen() - previousEventTime > 0) {
                robot.delay((int) (event.getWhen() - previousEventTime));
            }
            previousEventTime = event.getWhen();

            //replay mouse and keyboard events
            //right click missing
            //dragging is missing
            event.execute(robot);

        }

        setChanged();
        notifyObservers();
    }
}
