package com.eguller.mouserecorder.config;

import java.util.prefs.Preferences;

/**
 * User: eguller
 * Date: 3/8/14
 * Time: 9:24 AM
 */
public class PersistentConfig implements Config {
    private static final double DEFAULT_SPEED = 1.0;
    private Preferences preferences;
    private static final String MINIMIZE_ON_RECORD = "minimize_on_record";
    private static final String MINIMIZE_ON_PLAY = "minimize_on_play";
    private static final String INFINITE_LOOP = "loop";
    private static final String SPEED = "speed";
    private static final String LOOP_COUNT = "loop-count";
    private static final int DEFAULT_LOOP_COUNT = 1;

    public PersistentConfig() {
        preferences = Preferences.userRoot().node("MouseRecorder");
    }

    @Override
    public Config activateMinimizeOnPlay() {
        preferences.putBoolean(MINIMIZE_ON_PLAY, true);
        return this;
    }

    @Override
    public Config deactivateMinimizeOnPlay() {
        preferences.putBoolean(MINIMIZE_ON_PLAY, false);
        return this;
    }

    @Override
    public boolean getMinimizeOnPlay() {
        return preferences.getBoolean(MINIMIZE_ON_PLAY, false);
    }

    @Override
    public Config activateMinimizeOnRecord() {
        preferences.putBoolean(MINIMIZE_ON_RECORD, true);
        return this;
    }

    @Override
    public Config deactivateMinimizeOnRecord() {
        preferences.putBoolean(MINIMIZE_ON_RECORD, false);
        return this;
    }

    @Override
    public boolean getMinimizeOnRecord() {
        return preferences.getBoolean(MINIMIZE_ON_RECORD, false);
    }

    @Override
    public Config setLoopCount(int loopCount) {
        preferences.putInt(LOOP_COUNT, loopCount);
        return this;
    }

    @Override
    public int getLoopCount() {
        return preferences.getInt(LOOP_COUNT, DEFAULT_LOOP_COUNT);
    }

    @Override
    public Config activeInfiniteLoop() {
        preferences.putBoolean(INFINITE_LOOP, true);
        return this;
    }

    @Override
    public Config deactivateInfiniteLoop() {
        preferences.putBoolean(INFINITE_LOOP, false);
        return this;
    }

    @Override
    public boolean isInfiniteLoop() {
        return preferences.getBoolean(INFINITE_LOOP, false);
    }

    @Override
    public Config setSpeed(double speed) {
        preferences.putDouble(SPEED, speed);
        return this;
    }

    @Override
    public double getSpeed() {
        return preferences.getDouble(SPEED, DEFAULT_SPEED);
    }
}
