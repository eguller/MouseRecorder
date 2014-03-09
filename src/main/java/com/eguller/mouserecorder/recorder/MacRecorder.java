package com.eguller.mouserecorder.recorder;

import com.eguller.mouserecorder.config.Config;
import com.eguller.mouserecorder.jni.mac.DeviceAccess;

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
}
