package com.eguller.mouserecorder.recorder;

import com.eguller.mouserecorder.config.Config;
import com.eguller.mouserecorder.recorder.api.Recorder;
import com.eguller.mouserecorder.recorder.event.*;
import com.eguller.mouserecorder.wrappers.MouseWrapper;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.*;

/**
 * User: eguller
 * Date: 11/24/13
 * Time: 4:08 PM
 * <p/>
 * Record mouse and keyboard actions.
 */
public class BaseRecorder implements NativeMouseMotionListener, NativeKeyListener, NativeMouseListener, NativeMouseWheelListener, Recorder {


    Record record;
    //private static List<Event> inputEventList = new LinkedList<Event>();
    Config config;

    public BaseRecorder(Config config) {
        this.config = config;
        record = new Record(config);
    }

    public void record() {
        registerNativeHook();
        record = new Record(config);
    }

    public Record stop() {
        deRegisterNativeHook();
        return record;
    }

    @Override
    public Record getRecord() {
        return record;
    }

    @Override
    public void setRecord(Record record) {
        this.record = record;
    }

    /**
     * Register native hook and registerNativeHook capturing
     */
    public void registerNativeHook() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
        }

        GlobalScreen.getInstance().addNativeKeyListener(this);
        GlobalScreen.getInstance().addNativeMouseListener(this);
        GlobalScreen.getInstance().addNativeMouseMotionListener(this);
        GlobalScreen.getInstance().addNativeMouseWheelListener(this);

    }


    /**
     * De-register native hook and deRegisterNativeHook capturing
     */
    public void deRegisterNativeHook() {
        GlobalScreen.unregisterNativeHook();
        GlobalScreen.getInstance().removeNativeKeyListener(this);
        GlobalScreen.getInstance().removeNativeMouseListener(this);
        GlobalScreen.getInstance().removeNativeMouseMotionListener(this);
        GlobalScreen.getInstance().removeNativeMouseWheelListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        System.out.println("key code: " + nativeKeyEvent.getKeyCode());
        record.post(new KeyPressedEvent(nativeKeyEvent.getKeyCode()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        record.post(new KeyReleasedEvent(nativeKeyEvent.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        record.post(new MouseMoveEvent(nativeMouseEvent.getX(), nativeMouseEvent.getY()));
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        record.post(new MouseMoveEvent(nativeMouseEvent.getX(), nativeMouseEvent.getY()));
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        int maskCode = MouseWrapper.native2Code(nativeMouseEvent.getButton());
        record.post(new MousePressedEvent(maskCode));
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        int maskCode = MouseWrapper.native2Code(nativeMouseEvent.getButton());
        record.post(new MouseReleasedEvent(maskCode));
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        record.post(new MouseWheelEvent(nativeMouseWheelEvent.getWheelRotation()));
    }
}
