package com.eguller.mouserecorder.format;

import com.eguller.mouserecorder.format.api.Convertor;
import com.eguller.mouserecorder.format.def.DefaultFormat;
import com.eguller.mouserecorder.format.def.KeyWrapper;
import com.eguller.mouserecorder.format.def.MouseWrapper;
import com.eguller.mouserecorder.recorder.event.*;
import org.junit.Assert;
import org.junit.Test;

import java.awt.event.KeyEvent;

/**
 * User: eguller
 * Date: 2/25/14
 * Time: 7:23 AM
 */
public class DefaultFormatTest {
    DefaultFormat defaultFormat = new DefaultFormat();

    @Test
    public void delayEventConvertorTest() {
        DelayEvent delayEvent = new DelayEvent(1923);
        Convertor convertor = defaultFormat.getConvertor(delayEvent);
        String str = convertor.event2String(delayEvent);
        String expected = "{delay 1923}";
        Assert.assertEquals(str, expected);
    }

    @Test
    public void keyPressedEventConvertorTest() {
        KeyPressedEvent keyEvent = new KeyPressedEvent(KeyEvent.VK_R);
        Convertor convertor = defaultFormat.getConvertor(keyEvent);
        String str = convertor.event2String(keyEvent);
        String expected = "{R pressed}";
        Assert.assertEquals(str, expected);

    }

    @Test
    public void keyPressedEventConvertorTest2() {
        KeyPressedEvent keyEvent = new KeyPressedEvent(KeyEvent.VK_ENTER);
        Convertor convertor = defaultFormat.getConvertor(keyEvent);
        String str = convertor.event2String(keyEvent);
        String expected = "{ENTER pressed}";
        Assert.assertEquals(str, expected);
    }

    @Test
    public void keyReleasedEventConvertorTest() {
        KeyReleasedEvent keyEvent = new KeyReleasedEvent(KeyEvent.VK_R);
        Convertor convertor = defaultFormat.getConvertor(keyEvent);
        String str = convertor.event2String(keyEvent);
        String expected = "{R released}";
        Assert.assertEquals(str, expected);
    }

    @Test
    public void keyReleasedEventConvertorTest2() {
        KeyReleasedEvent keyEvent = new KeyReleasedEvent(KeyEvent.VK_ENTER);
        Convertor convertor = defaultFormat.getConvertor(keyEvent);
        String str = convertor.event2String(keyEvent);
        String expected = "{ENTER released}";
        Assert.assertEquals(str, expected);
    }

    @Test
    public void mouseLeftButtonEventConvertorTest() {
        MousePressedEvent mousePressedEvent = new MousePressedEvent(MouseWrapper.keyToCode("lmouse"));
        Convertor convertor = defaultFormat.getConvertor(mousePressedEvent);
        String str = convertor.event2String(mousePressedEvent);
        String expected = "{lmouse pressed}";
        Assert.assertEquals(str, expected);
    }

    @Test
    public void mouseRightButtonEventConvertorTest() {
        MousePressedEvent mousePressedEvent = new MousePressedEvent(MouseWrapper.keyToCode("rmouse"));
        Convertor convertor = defaultFormat.getConvertor(mousePressedEvent);
        String str = convertor.event2String(mousePressedEvent);
        String expected = "{rmouse pressed}";
        Assert.assertEquals(str, expected);

    }

    @Test
    public void mouseWheelButtonEventConvertorTest() {
        MousePressedEvent mousePressedEvent = new MousePressedEvent(MouseWrapper.keyToCode("wheel"));
        Convertor convertor = defaultFormat.getConvertor(mousePressedEvent);
        String str = convertor.event2String(mousePressedEvent);
        String expected = "{wheel pressed}";
        Assert.assertEquals(str, expected);

    }

    @Test
    public void mouseReleaseEventConvetorTest() {
        MouseReleasedEvent mouseReleasedEvent = new MouseReleasedEvent(MouseWrapper.keyToCode("lmouse"));
        Convertor convertor = defaultFormat.getConvertor(mouseReleasedEvent);
        String str = convertor.event2String(mouseReleasedEvent);
        String expected = "{lmouse released}";
        Assert.assertEquals(str, expected);
    }

    @Test
    public void mouseMoveEventTest() {
        MouseMoveEvent mouseMoveEvent = new MouseMoveEvent(350, 620);
        Convertor convertor = defaultFormat.getConvertor(mouseMoveEvent);
        String str = convertor.event2String(mouseMoveEvent);
        String expected = "{move (350, 620)}";
        Assert.assertEquals(str, expected);
    }

    @Test
    public void delayStr2DelayEventTest() {
        String str = "  { delay   356 } ";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(DelayEvent.class, event.getClass());
        DelayEvent delayEvent = (DelayEvent) event;
        Assert.assertEquals(delayEvent.getDelay(), 356);
        event = convertor.string2Event("{delay 442}");
        Assert.assertEquals(((DelayEvent) event).getDelay(), 442);
    }

    @Test
    public void keyPressedStr2KeyPressedEventTest() {
        String str = " {  R pressed   }  ";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(KeyPressedEvent.class, event.getClass());
        Assert.assertEquals(((KeyPressedEvent) event).getKey(), KeyWrapper.keyToCode("R"));
    }

    @Test
    public void enterKeyPressedStr2KeyPressedEventTest() {
        String str = "{ENTER pressed}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(KeyPressedEvent.class, event.getClass());
        Assert.assertEquals(((KeyPressedEvent) event).getKey(), KeyWrapper.keyToCode("ENTER"));
    }

    @Test
    public void lmousePressesIsNotAKeyEventTest() {
        String str = "{lmouse pressed}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(MousePressedEvent.class, event.getClass());
    }

    @Test
    public void keyReleasedStr2KeyPressedEventTest() {
        String str = " {  M released   }  ";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(KeyReleasedEvent.class, event.getClass());
        Assert.assertEquals(((KeyReleasedEvent) event).getKey(), KeyWrapper.keyToCode("M"));
    }

    @Test
    public void shiftKeyReleasedStr2KeyPressedEventTest() {
        String str = "{SHIFT released}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(KeyReleasedEvent.class, event.getClass());
        Assert.assertEquals(KeyWrapper.keyToCode("SHIFT"), ((KeyReleasedEvent) event).getKey());
    }

    @Test
    public void rmouseReleasedIsNotAKeyEventTest() {
        String str = "{rmouse released}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(MouseReleasedEvent.class, event.getClass());
    }

    @Test
    public void rMousePressedEventTest() {
        String str = "  {rmouse pressed }   ";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(MousePressedEvent.class, event.getClass());
        Assert.assertEquals(MouseWrapper.keyToCode("rmouse"), ((MousePressedEvent)event).getButton());
    }

    @Test
    public void lMousePressedEventTest() {
        String str = "{lmouse pressed}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(MousePressedEvent.class, event.getClass());
        Assert.assertEquals(MouseWrapper.keyToCode("lmouse"), ((MousePressedEvent)event).getButton());
    }

    @Test
    public void wheelPressedEventTest() {
        String str = "{ wheel pressed}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(MousePressedEvent.class, event.getClass());
        Assert.assertEquals(MouseWrapper.keyToCode("wheel"), ((MousePressedEvent)event).getButton());
    }

    @Test
    public void rMouseReleasedEventTest() {
        String str = "{rmouse released }";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(MouseReleasedEvent.class, event.getClass());
        Assert.assertEquals(MouseWrapper.keyToCode("rmouse"), ((MouseReleasedEvent)event).getButton());
    }

    @Test
    public void lMouseReleasedEventTest() {
        String str = "{lmouse released }";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(MouseReleasedEvent.class, event.getClass());
        Assert.assertEquals(MouseWrapper.keyToCode("lmouse"), ((MouseReleasedEvent)event).getButton());
    }

    @Test
    public void wheelReleasedEventTest() {
        String str = " {wheel   released}";
        Convertor convertor = defaultFormat.getConvertor(str);
        Event event = convertor.string2Event(str);
        Assert.assertEquals(MouseReleasedEvent.class, event.getClass());
        Assert.assertEquals(MouseWrapper.keyToCode("wheel"), ((MouseReleasedEvent)event).getButton());
    }


}
