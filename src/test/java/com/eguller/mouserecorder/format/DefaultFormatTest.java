package com.eguller.mouserecorder.format;

import com.eguller.mouserecorder.recorder.event.*;
import org.junit.Assert;
import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * User: eguller
 * Date: 2/25/14
 * Time: 7:23 AM
 */
public class DefaultFormatTest {
    DefaultFormat defaultFormat = new DefaultFormat();
    @Test
    public void delayEventConvertorTest(){
        DelayEvent delayEvent = new DelayEvent(1923);
        Convertor convertor = defaultFormat.getConvertor(delayEvent);
        String str = convertor.event2String(delayEvent);
        String expected = "{delay 1923}";
        Assert.assertEquals(str,expected);
    }
    @Test
    public void keyPressedEventConvertorTest(){
        KeyPressedEvent keyEvent = new KeyPressedEvent(KeyEvent.VK_R);
        Convertor convertor = defaultFormat.getConvertor(keyEvent);
        String str = convertor.event2String(keyEvent);
        String expected = "{R pressed}";
        Assert.assertEquals(str,expected);

    }
    @Test
    public void keyReleasedEventConvertorTest(){
        KeyReleasedEvent keyEvent = new KeyReleasedEvent(KeyEvent.VK_R);
        Convertor convertor = defaultFormat.getConvertor(keyEvent);
        String str = convertor.event2String(keyEvent);
        String expected = "{R released}";
        Assert.assertEquals(str,expected);
    }

    @Test
    public void mouseLeftButtonEventConvertorTest(){
        MousePressedEvent mousePressedEvent = new MousePressedEvent(1);
        Convertor convertor = defaultFormat.getConvertor(mousePressedEvent);
        String str = convertor.event2String(mousePressedEvent);
        String expected = "{lmouse pressed}";
        Assert.assertEquals(str, expected);
    }

    @Test
    public void mouseRightButtonEventConvertorTest(){
        MousePressedEvent mousePressedEvent = new MousePressedEvent(3);
        Convertor convertor = defaultFormat.getConvertor(mousePressedEvent);
        String str = convertor.event2String(mousePressedEvent);
        String expected = "{rmouse pressed}";
        Assert.assertEquals(str,expected);

    }

    @Test
    public void mouseWheelButtonEventConvertorTest(){
        MousePressedEvent mousePressedEvent = new MousePressedEvent(2);
        Convertor convertor = defaultFormat.getConvertor(mousePressedEvent);
        String str = convertor.event2String(mousePressedEvent);
        String expected = "{wheel pressed}";
        Assert.assertEquals(str,expected);

    }

    @Test
    public void mouseReleaseEventConvetorTest(){
        MouseReleasedEvent mouseReleasedEvent = new MouseReleasedEvent(1);
        Convertor convertor = defaultFormat.getConvertor(mouseReleasedEvent);
        String str = convertor.event2String(mouseReleasedEvent);
        String expected = "{lmouse released}";
        Assert.assertEquals(str,expected);
    }
}
