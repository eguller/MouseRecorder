package com.eguller.mouserecorder.recorder;

import com.eguller.mouserecorder.jni.mac.DeviceAccess;

/**
 * User: eguller
 * Date: 2/21/14
 * Time: 11:45 PM
 */
public class MacRecorder extends BaseRecorder {
    public MacRecorder() {
        super();
    }
    @Override
    public void record(){
        DeviceAccess.enableIfDisabled();
        super.record();
    }
}
