package com.eguller.mouserecorder.format;

import com.eguller.mouserecorder.config.Config;
import com.eguller.mouserecorder.config.VolatileConfig;
import com.eguller.mouserecorder.format.api.Convertor;
import com.eguller.mouserecorder.format.def.DefaultFormat;
import com.eguller.mouserecorder.format.def.KeyWrapper;
import com.eguller.mouserecorder.format.def.MouseWrapper;
import com.eguller.mouserecorder.recorder.event.*;
import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: eguller
 * Date: 2/25/14
 * Time: 7:23 AM
 */
public class DefaultFormatTest {
    Config config = new VolatileConfig();
    DefaultFormat defaultFormat = new DefaultFormat(config);

    @Test
    public void delayEventConvertorTest() {
        DelayEvent delayEvent = new DelayEvent(1923, config);
        Convertor convertor = defaultFormat.getConvertor(delayEvent);
        String str = convertor.event2String(delayEvent);
        String expected = "{delay 1923}";
        assertEquals(str, expected);
    }

    @Test
    public void keyPressedEventConvertorTest() {
        KeyPressedEvent keyEvent = new KeyPressedEvent(KeyEvent.VK_R);
        Convertor convertor = defaultFormat.getConvertor(keyEvent);
        String str = convertor.event2String(keyEvent);
        String expected = "{R pressed}";
        assertEquals(str, expected);

    }

    @Test
    public void keyPressedEventConvertorTest2() {
        KeyPressedEvent keyEvent = new KeyPressedEvent(KeyEvent.VK_ENTER);
        Convertor convertor = defaultFormat.getConvertor(keyEvent);
        String str = convertor.event2String(keyEvent);
        String expected = "{ENTER pressed}";
        assertEquals(str, expected);
    }

    @Test
    public void keyReleasedEventConvertorTest() {
        KeyReleasedEvent keyEvent = new KeyReleasedEvent(KeyEvent.VK_R);
        Convertor convertor = defaultFormat.getConvertor(keyEvent);
        String str = convertor.event2String(keyEvent);
        String expected = "{R released}";
        assertEquals(str, expected);
    }

    @Test
    public void keyReleasedEventConvertorTest2() {
        KeyReleasedEvent keyEvent = new KeyReleasedEvent(KeyEvent.VK_ENTER);
        Convertor convertor = defaultFormat.getConvertor(keyEvent);
        String str = convertor.event2String(keyEvent);
        String expected = "{ENTER released}";
        assertEquals(str, expected);
    }

    @Test
    public void mouseLeftButtonEventConvertorTest() {
        MousePressedEvent mousePressedEvent = new MousePressedEvent(MouseWrapper.keyToCode("lmouse"));
        Convertor convertor = defaultFormat.getConvertor(mousePressedEvent);
        String str = convertor.event2String(mousePressedEvent);
        String expected = "{lmouse pressed}";
        assertEquals(str, expected);
    }

    @Test
    public void mouseRightButtonEventConvertorTest() {
        MousePressedEvent mousePressedEvent = new MousePressedEvent(MouseWrapper.keyToCode("rmouse"));
        Convertor convertor = defaultFormat.getConvertor(mousePressedEvent);
        String str = convertor.event2String(mousePressedEvent);
        String expected = "{rmouse pressed}";
        assertEquals(str, expected);

    }

    @Test
    public void mouseWheelButtonEventConvertorTest() {
        MousePressedEvent mousePressedEvent = new MousePressedEvent(MouseWrapper.keyToCode("wheel"));
        Convertor convertor = defaultFormat.getConvertor(mousePressedEvent);
        String str = convertor.event2String(mousePressedEvent);
        String expected = "{wheel pressed}";
        assertEquals(str, expected);

    }

    @Test
    public void mouseReleaseEventConvetorTest() {
        MouseReleasedEvent mouseReleasedEvent = new MouseReleasedEvent(MouseWrapper.keyToCode("lmouse"));
        Convertor convertor = defaultFormat.getConvertor(mouseReleasedEvent);
        String str = convertor.event2String(mouseReleasedEvent);
        String expected = "{lmouse released}";
        assertEquals(str, expected);
    }

    @Test
    public void mouseMoveEventConvertorTest() {
        MouseMoveEvent mouseMoveEvent = new MouseMoveEvent(350, 620);
        Convertor convertor = defaultFormat.getConvertor(mouseMoveEvent);
        String str = convertor.event2String(mouseMoveEvent);
        String expected = "{move (350, 620)}";
        assertEquals(str, expected);
    }

    @Test
    public void mouseWheelEventConvertorTest() {
        int scrollAmount = -3;
        MouseWheelEvent mouseWheelEvent = new MouseWheelEvent(scrollAmount);
        Convertor convertor = defaultFormat.getConvertor(mouseWheelEvent);
        assertNotNull(convertor);
        String expected = String.format("{wheel %d}", scrollAmount);
        String actual = convertor.event2String(mouseWheelEvent);
        assertEquals(expected, actual);
    }

    @Test
    public void delayStr2DelayEventTest() {
        String str = "  { delay   356 } ";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(DelayEvent.class, event.getClass());
        DelayEvent delayEvent = (DelayEvent) event;
        assertEquals(delayEvent.getDelay(), 356);
        event = convertor.string2Event("{delay 442}");
        assertEquals(((DelayEvent) event).getDelay(), 442);
    }

    @Test
    public void keyPressedStr2KeyPressedEventTest() {
        String str = " {  R pressed   }  ";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(KeyPressedEvent.class, event.getClass());
        assertEquals(((KeyPressedEvent) event).getKey(), KeyWrapper.keyToCode("R"));
    }

    @Test
    public void enterKeyPressedStr2KeyPressedEventTest() {
        String str = "{ENTER pressed}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(KeyPressedEvent.class, event.getClass());
        assertEquals(((KeyPressedEvent) event).getKey(), KeyWrapper.keyToCode("ENTER"));
    }

    @Test
    public void lmousePressesIsNotAKeyEventTest() {
        String str = "{lmouse pressed}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(MousePressedEvent.class, event.getClass());
    }

    @Test
    public void keyReleasedStr2KeyPressedEventTest() {
        String str = " {  M released   }  ";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(KeyReleasedEvent.class, event.getClass());
        assertEquals(((KeyReleasedEvent) event).getKey(), KeyWrapper.keyToCode("M"));
    }

    @Test
    public void shiftKeyReleasedStr2KeyPressedEventTest() {
        String str = "{SHIFT released}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(KeyReleasedEvent.class, event.getClass());
        assertEquals(KeyWrapper.keyToCode("SHIFT"), ((KeyReleasedEvent) event).getKey());
    }

    @Test
    public void rmouseReleasedIsNotAKeyEventTest() {
        String str = "{rmouse released}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(MouseReleasedEvent.class, event.getClass());
    }

    @Test
    public void rMousePressedEventTest() {
        String str = "  {rmouse pressed }   ";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(MousePressedEvent.class, event.getClass());
        assertEquals(MouseWrapper.keyToCode("rmouse"), ((MousePressedEvent) event).getButton());
    }

    @Test
    public void lMousePressedEventTest() {
        String str = "{lmouse pressed}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(MousePressedEvent.class, event.getClass());
        assertEquals(MouseWrapper.keyToCode("lmouse"), ((MousePressedEvent) event).getButton());
    }

    @Test
    public void wheelPressedEventTest() {
        String str = "{ wheel pressed}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(MousePressedEvent.class, event.getClass());
        assertEquals(MouseWrapper.keyToCode("wheel"), ((MousePressedEvent) event).getButton());
    }

    @Test
    public void rMouseReleasedEventTest() {
        String str = "{rmouse released }";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(MouseReleasedEvent.class, event.getClass());
        assertEquals(MouseWrapper.keyToCode("rmouse"), ((MouseReleasedEvent) event).getButton());
    }

    @Test
    public void lMouseReleasedEventTest() {
        String str = "{lmouse released }";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(MouseReleasedEvent.class, event.getClass());
        assertEquals(MouseWrapper.keyToCode("lmouse"), ((MouseReleasedEvent) event).getButton());
    }

    @Test
    public void wheelReleasedEventTest() {
        String str = " {wheel   released}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        assertEquals(MouseReleasedEvent.class, event.getClass());
        assertEquals(MouseWrapper.keyToCode("wheel"), ((MouseReleasedEvent) event).getButton());
    }

    @Test
    public void mouseMoveEventTest() {
        int x = 56;
        int y = 99;
        String str1 = String.format("{move (%d,%d)}", x, y);
        Convertor convertor = defaultFormat.getConvertor(str1);
        Event event = convertor.string2Event(str1);
        assertEquals(event.getClass(), MouseMoveEvent.class);
        MouseMoveEvent mouseMoveEvent = (MouseMoveEvent) event;
        assertEquals(x, mouseMoveEvent.getX());
        assertEquals(y, mouseMoveEvent.getY());
    }

    @Test
    public void mouseWheelEventTest() {
        for (int i = 0; i < 5; i++) {
            int scrollAmount = new Random().nextInt(10) * Math.random() < 0.5 ? -1 : 1;
            String str = String.format("{wheel %d}", scrollAmount);
            Convertor convertor = defaultFormat.getConvertor(str);
            Event event = convertor.string2Event(str);
            assertEquals(event.getClass(), MouseWheelEvent.class);
            MouseWheelEvent mouseWheelEvent = (MouseWheelEvent) event;
            int parsedScrollAmount = mouseWheelEvent.getScrollAmount();
            assertEquals(scrollAmount, parsedScrollAmount);
        }
    }

}
