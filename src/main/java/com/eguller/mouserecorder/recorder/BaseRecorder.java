package com.eguller.mouserecorder.recorder;

import com.eguller.mouserecorder.config.Config;
import com.eguller.mouserecorder.format.def.MouseWrapper;
import com.eguller.mouserecorder.recorder.api.Recorder;
import com.eguller.mouserecorder.recorder.event.*;
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

        //Construct the example object and initialze native hook.
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
        record.post(new KeyPressedEvent(nativeKeyEvent.getKeyCode()));
        System.out.println("Key pressed: " + nativeKeyEvent.getKeyCode());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        record.post(new KeyReleasedEvent(nativeKeyEvent.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        //inputEventList.add(nativeKeyEvent);
        //System.out.println("key typed");
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        record.post(new MouseMoveEvent(nativeMouseEvent.getX(), nativeMouseEvent.getY()));
        //System.out.println("Mouse Moved - button: " + nativeMouseEvent.getButton() + " modifier: " + nativeMouseEvent.getModifiers());
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        //System.err.println("Unhandled event: " + nativeMouseEvent);
        System.out.println("Mouse Dragged - button: " + nativeMouseEvent.getButton() + " modifier: " + nativeMouseEvent.getModifiers());
        //inputEventList.add(new MousePressedEvent(nativeMouseEvent.getButton()));
        record.post(new MouseMoveEvent(nativeMouseEvent.getX(), nativeMouseEvent.getY()));
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        //System.err.println("Unhandled event: " + nativeMouseEvent);
        System.out.println("Mouse Clicked - button: " + nativeMouseEvent.getButton() + " modifier: " + nativeMouseEvent.getModifiers());
        //System.out.println("mask: " + MouseEvent.BUTTON1_DOWN_MASK);
        //inputEventList.add(new MousePressedEvent(nativeMouseEvent.getButton()));
        //record.post(new MousePressedEvent(nativeMouseEvent.getButton()));

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        //inputEventList.add(nativeMouseEvent);
        System.out.println("Mouse Pressed - button: " + nativeMouseEvent.getButton() + " modifier: " + nativeMouseEvent.getModifiers());
        int maskCode = MouseWrapper.native2Code(nativeMouseEvent.getButton());
        record.post(new MousePressedEvent(maskCode));
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        int maskCode = MouseWrapper.native2Code(nativeMouseEvent.getButton());
        record.post(new MouseReleasedEvent(maskCode));
        System.out.println("Mouse Released - button: " + nativeMouseEvent.getButton() + " modifier: " + nativeMouseEvent.getModifiers());
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        System.out.println("Mouse Wheel moved - button: " + nativeMouseWheelEvent.getButton() + " modifier: " + nativeMouseWheelEvent.getModifiers() + " other: " + nativeMouseWheelEvent.getWheelRotation());
        record.post(new MouseWheelEvent(nativeMouseWheelEvent.getWheelRotation()));
    }
}
