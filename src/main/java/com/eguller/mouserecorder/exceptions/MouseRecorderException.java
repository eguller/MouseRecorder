package com.eguller.mouserecorder.exceptions;

import com.eguller.mouserecorder.MouseRecorder;

/**
 * User: eguller
 * Date: 2/23/14
 * Time: 3:00 AM
 */
public class MouseRecorderException extends RuntimeException {
    boolean popup = false;
    boolean log = false;

    public MouseRecorderException(String message) {
        super(message);
    }

    public MouseRecorderException(String message, Throwable t) {
        super(message, t);
    }

    public MouseRecorderException popup() {
        popup = true;
        return this;
    }

    public MouseRecorderException log() {
        this.log = true;
        return this;
    }

    public MouseRecorderException immeadiateLog() {
        //TODO - immediate logging here
        return this;
    }
}
