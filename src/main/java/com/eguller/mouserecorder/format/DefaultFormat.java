package com.eguller.mouserecorder.format;

import com.eguller.mouserecorder.exceptions.MouseRecorderException;
import com.eguller.mouserecorder.recorder.Record;
import com.eguller.mouserecorder.recorder.event.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: eguller
 * Date: 2/23/14
 * Time: 2:38 AM
 */
public class DefaultFormat implements Format {
    private static final String LINE_SEPERATOR = System.getProperty("line.separator");
    Map<Class, Convertor> convertorFromClass = new HashMap<Class, Convertor>();
    Map<String, Convertor> convertorFromRegexp = new HashMap<String, Convertor>();

    public DefaultFormat() {
        convertorFromClass.put(DelayEvent.class, delayConvertor);
        convertorFromClass.put(KeyPressedEvent.class, keyPressedConvertor);
        convertorFromClass.put(KeyReleasedEvent.class, keyReleasedConvertor);
        convertorFromClass.put(MouseClickEvent.class, mouseClickConvertor);
        convertorFromClass.put(MouseReleaseEvent.class, mouseReleaseConvertor);
        convertorFromClass.put(MouseMoveEvent.class, mouseMoveConvertor);

        convertorFromRegexp.put("delay_event_regexp", delayConvertor);
        convertorFromRegexp.put("key_pressed_event_regexp", keyPressedConvertor);
        convertorFromRegexp.put("key_released_event_regexp", keyReleasedConvertor);
        convertorFromRegexp.put("mouse_clicked_event_regexp", mouseClickConvertor);
        convertorFromRegexp.put("mouse_released_event_regexp", mouseReleaseConvertor);
        convertorFromRegexp.put("mouse_moved_event,regexp", mouseMoveConvertor);
    }

    @Override
    public void save(File file, Record record) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Event event : record.getEventList()) {
                Convertor convertor = getConvertor(event);
                if (convertor != null) {
                    bw.write(convertor.event2String(event));
                    bw.write(LINE_SEPERATOR);

                } else {
                    //TODO - Also log here
                    System.err.println("Convertor not found for class: " + event.getClass());
                }
            }
        } catch (IOException e) {
            throw new MouseRecorderException(String.format("File %s cannot be saved. \n" +
                    "Please check that you have enough disk space or\n" +
                    "Enough permissions to write file.")).popup().immeadiateLog();
        }

    }

    @Override
    public Record load(File file) {
        Record record = new Record();
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                Convertor convertor = getConvertor(line);
                if (convertor != null) {
                    Event event = convertor.string2Event(line);
                    record.add(event);
                }
            }
        } catch (IOException e) {
            throw new MouseRecorderException(String.format("File %s cannot be loaded. \n")).popup().immeadiateLog();
        }
        return record;
    }

    @Override
    public Convertor getConvertor(Event event) {
        return convertorFromClass.get(event.getClass());
    }

    @Override
    public Convertor getConvertor(String str) {
        return convertorFromRegexp.get(str);
    }

    Convertor delayConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            return null;
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };

    Convertor keyPressedConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            return null;
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };

    Convertor keyReleasedConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            return null;
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };

    Convertor mouseClickConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            return null;
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };

    Convertor mouseReleaseConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            return null;
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };

    Convertor mouseMoveConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            return null;
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };
}
