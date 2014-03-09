package com.eguller.mouserecorder.player;

import com.eguller.mouserecorder.config.Config;
import com.eguller.mouserecorder.player.api.Player;
import com.eguller.mouserecorder.recorder.Record;
import com.eguller.mouserecorder.recorder.event.Event;

import java.awt.*;
import java.util.Observable;
import java.util.concurrent.Executor;
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
    Config config = null;

    public PlayerImpl(Config config) throws AWTException {
        this.config = config;
        this.robot = new Robot();
    }

    public void play(Record record) {
        this.record = record;
        executor.execute(this);
    }

    @Override
    public void run() {
        long previousEventTime = -1;
        for (Event event : record.getEventList()) {
            event.execute(robot);
        }
        setChanged();
        notifyObservers();
    }
}
