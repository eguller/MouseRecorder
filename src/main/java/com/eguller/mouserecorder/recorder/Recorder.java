package com.eguller.mouserecorder.recorder;

import com.eguller.mouserecorder.recorder.event.*;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.*;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * User: eguller
 * Date: 11/24/13
 * Time: 4:08 PM
 *
 * Record mouse and keyboard actions.
 */
public class Recorder implements NativeMouseMotionListener, NativeKeyListener, NativeMouseListener, NativeMouseWheelListener {


    boolean registered = false;
    private static final Recorder recorder = new Recorder();
    private static List<Event> inputEventList = new LinkedList<Event>();

    private Recorder(){}

    /**
     * Register native hook and start capturing
     */
    public static void start() {
        if (!recorder.isRegistered()) {
            try {
                inputEventList = new LinkedList<Event>();
                GlobalScreen.registerNativeHook();
                recorder.setRegistered(true);
            } catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());
            }

            //Construct the example object and initialze native hook.
            GlobalScreen.getInstance().addNativeKeyListener(recorder);
            GlobalScreen.getInstance().addNativeMouseListener(recorder);
            GlobalScreen.getInstance().addNativeMouseMotionListener(recorder);
            GlobalScreen.getInstance().addNativeMouseWheelListener(recorder);
        }
    }

    /**
     * De-register native hook and stop capturing
     */
    public static  void stop(){
         if(recorder.isRegistered()){
             GlobalScreen.unregisterNativeHook();
             GlobalScreen.getInstance().removeNativeKeyListener(recorder);
             GlobalScreen.getInstance().removeNativeMouseListener(recorder);
             GlobalScreen.getInstance().removeNativeMouseMotionListener(recorder);
             GlobalScreen.getInstance().removeNativeMouseWheelListener(recorder);
             recorder.setRegistered(false);
         }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        inputEventList.add(new KeyPressedEvent(nativeKeyEvent.getKeyCode()));
        System.out.println("Key pressed: " + nativeKeyEvent.getKeyCode());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
       inputEventList.add(new KeyReleasedEvent(nativeKeyEvent.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        //inputEventList.add(nativeKeyEvent);
        //System.out.println("key typed");
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        inputEventList.add(new MouseMoveEvent(nativeMouseEvent.getX(), nativeMouseEvent.getY()));
        //System.out.println("Mouse Moved - button: " + nativeMouseEvent.getButton() + " modifier: " + nativeMouseEvent.getModifiers());
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        //System.err.println("Unhandled event: " + nativeMouseEvent);
        System.out.println("Mouse Dragged - button: " + nativeMouseEvent.getButton() + " modifier: " + nativeMouseEvent.getModifiers());
        //inputEventList.add(new MouseClickEvent(nativeMouseEvent.getButton()));
        inputEventList.add(new MouseMoveEvent(nativeMouseEvent.getX(), nativeMouseEvent.getY()));
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        //System.err.println("Unhandled event: " + nativeMouseEvent);
        System.out.println("Mouse Clicked - button: " + nativeMouseEvent.getButton() + " modifier: " + nativeMouseEvent.getModifiers());
        //System.out.println("mask: " + MouseEvent.BUTTON1_DOWN_MASK);
        //inputEventList.add(new MouseClickEvent(nativeMouseEvent.getButton()));

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        //inputEventList.add(nativeMouseEvent);
        System.out.println("Mouse Pressed - button: " + nativeMouseEvent.getButton() + " modifier: " + nativeMouseEvent.getModifiers());
        inputEventList.add(new MouseClickEvent(nativeMouseEvent.getButton()));
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
       inputEventList.add(new MouseReleaseEvent(nativeMouseEvent.getButton()));
       System.out.println("Mouse Released - button: " + nativeMouseEvent.getButton() + " modifier: " + nativeMouseEvent.getModifiers());
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    /**
     * Get list of recorded events
     * @return - List of recorded events
     */
    public static List<Event> getRecordedEvents(){
        return inputEventList;
    }


    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        System.out.println("Mouse Wheel moved - button: " + nativeMouseWheelEvent.getButton() + " modifier: " + nativeMouseWheelEvent.getModifiers() + " other: " + nativeMouseWheelEvent.getWheelRotation());
    }
}
