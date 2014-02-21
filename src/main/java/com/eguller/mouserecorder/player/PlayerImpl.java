package com.eguller.mouserecorder.player;

import com.eguller.mouserecorder.player.api.Player;
import com.eguller.mouserecorder.recorder.Record;
import com.eguller.mouserecorder.recorder.event.Event;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: eguller
 * Date: 11/24/13
 * Time: 5:20 PM
 * <p/>
 * Replays given set of recorded mouse and keyboard actions
 */
public class PlayerImpl extends Observable implements Runnable, Player {
    Robot robot;
    Record record;
    Executor executor = Executors.newSingleThreadExecutor();
    public PlayerImpl() throws AWTException {
        robot = new Robot();
    }

    public void play(Record record){
        this.record = record;
        executor.execute(this);
    }

    @Override
    public void run() {
        long previousEventTime = -1;
        for (Event event : record.getEventList()) {
            //delay between events. This part will be parametrized to replay
            //events slower or faster.
            if (previousEventTime > 0 && event.getWhen() - previousEventTime > 0) {
                robot.delay((int) (event.getWhen() - previousEventTime));
            }
            previousEventTime = event.getWhen();
            event.execute(robot);
        }
        setChanged();
        notifyObservers();
    }
}
