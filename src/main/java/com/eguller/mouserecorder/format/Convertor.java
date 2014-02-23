package com.eguller.mouserecorder.format;

import com.eguller.mouserecorder.recorder.event.Event;

/**
 * User: eguller
 * Date: 2/23/14
 * Time: 2:42 AM
 */
public interface Convertor {
   public String event2String(Event event);
   public Event string2Event(String str);
}
