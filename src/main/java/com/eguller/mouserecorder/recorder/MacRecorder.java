package com.eguller.mouserecorder.recorder;

import com.eguller.mouserecorder.config.Config;
import com.eguller.mouserecorder.jni.mac.DeviceAccess;
import com.eguller.mouserecorder.recorder.event.KeyPressedEvent;
import com.eguller.mouserecorder.recorder.event.KeyReleasedEvent;
import com.eguller.mouserecorder.wrappers.KeyWrapper;
import org.jnativehook.keyboard.NativeKeyEvent;

/**
 * User: eguller
 * Date: 2/21/14
 * Time: 11:45 PM
 */
public class MacRecorder extends BaseRecorder {
    public MacRecorder(Config config) {
        super(config);
    }

    @Override
    public void record() {
        DeviceAccess.enableIfDisabled();
        super.record();
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        int keyCode = keyConverter(nativeKeyEvent);
        KeyPressedEvent keyPressedEvent = new KeyPressedEvent(keyCode);
        record.post(keyPressedEvent);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        int keyCode = keyConverter(nativeKeyEvent);
        KeyReleasedEvent keyReleasedEvent = new KeyReleasedEvent(keyCode);
        record.post(keyReleasedEvent);
    }

    public int keyConverter(NativeKeyEvent event) {
        if (event.getRawCode() == 56) {
            return KeyWrapper.keyToCode("SHIFT");
        } else if (event.getRawCode() == 59) {
            return KeyWrapper.keyToCode("CONTROL");
        } else if (event.getRawCode() == 55) {
            return KeyWrapper.keyToCode("META");
        } else if (event.getRawCode() == 58) {
            return KeyWrapper.keyToCode("ALT");
        } else {
            return event.getKeyCode();
        }

    }
}
