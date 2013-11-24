package com.eguller.mouserecorder.recorder;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;

import java.util.LinkedList;
import java.util.List;

/**
 * User: eguller
 * Date: 11/24/13
 * Time: 4:08 PM
 *
 * Record mouse and keyboard actions.
 */
public class Recorder implements NativeMouseMotionListener, NativeKeyListener, NativeMouseListener {


    boolean registered = false;
    private static final Recorder recorder = new Recorder();
    private static List<NativeInputEvent> inputEventList = new LinkedList<NativeInputEvent>();

    private Recorder(){}

    /**
     * Register native hook and start capturing
     */
    public static void start() {
        if (!recorder.isRegistered()) {
            try {
                inputEventList = new LinkedList<NativeInputEvent>();
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
             recorder.setRegistered(false);
         }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        inputEventList.add(nativeKeyEvent);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        inputEventList.add(nativeKeyEvent);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        inputEventList.add(nativeKeyEvent);
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        inputEventList.add(nativeMouseEvent);
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        inputEventList.add(nativeMouseEvent);
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
       inputEventList.add(nativeMouseEvent);
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
    public static List<NativeInputEvent> getRecordedEvents(){
        return inputEventList;
    }

}
