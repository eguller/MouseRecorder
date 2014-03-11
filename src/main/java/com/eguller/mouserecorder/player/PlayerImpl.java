package com.eguller.mouserecorder.player;

import com.eguller.mouserecorder.config.Config;
import com.eguller.mouserecorder.player.api.LoopEventListener;
import com.eguller.mouserecorder.player.api.Player;
import com.eguller.mouserecorder.player.event.LoopStartedEvent;
import com.eguller.mouserecorder.recorder.Record;
import com.eguller.mouserecorder.recorder.event.Event;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
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
    List<LoopEventListener> loopEventListenerList = new LinkedList<LoopEventListener>();

    public PlayerImpl(Config config) throws AWTException {
        this.config = config;
        this.robot = new Robot();
    }

    public void play(Record record) {
        this.record = record;
        executor.execute(this);
    }

    @Override
    public void addLoopEventListener(LoopEventListener loopEventListener) {
        loopEventListenerList.add(loopEventListener);
    }

    @Override
    public void notifyLoopStarted(LoopStartedEvent loopStartedEvent) {
        for (LoopEventListener listener : loopEventListenerList) {
            listener.loopStarted(loopStartedEvent);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < config.getLoopCount() || config.isInfiniteLoop(); i++) {
            notifyLoopStarted(new LoopStartedEvent(config.isInfiniteLoop(), i, config.getLoopCount()));
            for (Event event : record.getEventList()) {
                event.execute(robot);
            }
        }
        setChanged();
        notifyObservers();
    }
}
