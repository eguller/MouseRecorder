package com.eguller.mouserecorder.format;

import com.eguller.mouserecorder.recorder.Record;
import com.eguller.mouserecorder.recorder.event.Event;

import java.io.File;
import java.io.IOException;

/**
 * User: eguller
 * Date: 2/23/14
 * Time: 2:36 AM
 */
public interface Format {
    public void save(File file, Record record);
    public Record load(File file);
    public Convertor getConvertor(Event event);
    public Convertor getConvertor(String str);
}
