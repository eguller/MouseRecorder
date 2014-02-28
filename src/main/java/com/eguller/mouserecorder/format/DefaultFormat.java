package com.eguller.mouserecorder.format;

import com.eguller.mouserecorder.exceptions.MouseRecorderException;
import com.eguller.mouserecorder.recorder.Record;
import com.eguller.mouserecorder.recorder.event.*;

import java.awt.event.*;
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
        convertorFromClass.put(MousePressedEvent.class, mousePressedConvertor);
        convertorFromClass.put(MouseReleasedEvent.class, mouseReleasedConvertor);
        convertorFromClass.put(MouseMoveEvent.class, mouseMoveConvertor);

        convertorFromRegexp.put("delay_event_regexp", delayConvertor);
        convertorFromRegexp.put("key_pressed_event_regexp", keyPressedConvertor);
        convertorFromRegexp.put("key_released_event_regexp", keyReleasedConvertor);
        convertorFromRegexp.put("mouse_clicked_event_regexp", mousePressedConvertor);
        convertorFromRegexp.put("mouse_released_event_regexp", mouseReleasedConvertor);
        convertorFromRegexp.put("mouse_moved_event,regexp", mouseMoveConvertor);
    }

    @Override
    public void save(File file, Record record) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Event event : record.getEventList()) {
                Convertor convertor = getConvertor(event);
                if (convertor != null) {
                    String str = convertor.event2String(event);
                    bw.write(str);
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
        } finally {
            if(bw != null){
                try {
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    //TODO - log here
                    e.printStackTrace();
                }

            }
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
            DelayEvent delayEvent = (DelayEvent)event;
            return String.format("{delay %d}", delayEvent.getDelay());
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };

    Convertor keyPressedConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            KeyPressedEvent keyPressedEvent = (KeyPressedEvent)event;
            return String.format("{%s pressed}", KeyWrapper.codeToKey(keyPressedEvent.getKey()));
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };

    Convertor keyReleasedConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            KeyReleasedEvent keyPressedEvent = (KeyReleasedEvent)event;
            return String.format("{%s released}", KeyWrapper.codeToKey(keyPressedEvent.getKey()));
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };

    Convertor mousePressedConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            String str = "";
            MousePressedEvent mousePressedEvent = (MousePressedEvent)event;
            int button = mousePressedEvent.getButton();
            if(button == MouseEvent.BUTTON1_MASK){
                str = "{lmouse pressed}";
            } else if(button == MouseEvent.BUTTON2_MASK){
                str = "{rmouse pressed}";
            } else if(button == MouseEvent.BUTTON3_MASK){
                str = "{wheel pressed}";
            }
            return str;
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };

    Convertor mouseReleasedConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            String str = "";
            MouseReleasedEvent mousePressedEvent = (MouseReleasedEvent)event;
            int button = mousePressedEvent.getButton();
            if(button == MouseEvent.BUTTON1_MASK){
                str = "{lmouse released}";
            } else if(button == MouseEvent.BUTTON2_MASK){
                str = "{rmouse released}";
            } else if(button == MouseEvent.BUTTON3_MASK){
                str = "{wheel released}";
            }
            return str;
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };

    Convertor mouseMoveConvertor = new Convertor() {
        @Override
        public String event2String(Event event) {
            MouseMoveEvent mouseMoveEvent = (MouseMoveEvent)event;
            return String.format("{move (%d, %d)}", mouseMoveEvent.getX(), mouseMoveEvent.getY());
        }

        @Override
        public Event string2Event(String str) {
            return null;
        }
    };
}
