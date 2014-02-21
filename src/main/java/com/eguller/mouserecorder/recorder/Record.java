package com.eguller.mouserecorder.recorder;

import com.eguller.mouserecorder.recorder.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * User: eguller
 * Date: 2/22/14
 * Time: 12:06 AM
 */
public class Record {
    private List<Event> eventList = new ArrayList<Event>();

    public void addEvent(Event event){
        eventList.add(event);
    }
    public List<Event> getEventList(){
         return eventList;
    }
}
