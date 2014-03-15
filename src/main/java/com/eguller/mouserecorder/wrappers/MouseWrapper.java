package com.eguller.mouserecorder.wrappers;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * User: eguller
 * Date: 3/5/14
 * Time: 7:05 AM
 */
public class MouseWrapper {
    private static final String NONE_KEY = "none";
    private static final Integer NONE_CODE = -1;
    private static final Map<String, Integer> keyCode = new HashMap<String, Integer>();
    private static final Map<Integer, String> codeKey = new HashMap<Integer, String>();
    private static final Map<Integer, Integer> native2Code = new HashMap<Integer, Integer>();

    static {
        keyCode.put("lmouse", MouseEvent.BUTTON1_MASK);
        codeKey.put(MouseEvent.BUTTON1_MASK, "lmouse");
        native2Code.put(1, MouseEvent.BUTTON1_MASK);

        keyCode.put("rmouse", MouseEvent.BUTTON2_MASK);
        codeKey.put(MouseEvent.BUTTON2_MASK, "rmouse");
        native2Code.put(3, MouseEvent.BUTTON2_MASK);

        keyCode.put("wheel", MouseEvent.BUTTON3_MASK);
        codeKey.put(MouseEvent.BUTTON3_MASK, "wheel");
        native2Code.put(2, MouseEvent.BUTTON3_MASK);
    }

    public static String codeToKey(int code){
        String key = codeKey.get(code);
        return key == null ? NONE_KEY : key;
    }

    public static int keyToCode(String key){
        Integer code = keyCode.get(key);
        return code == null ?  NONE_CODE : code;
    }

    public static int native2Code(int ntv){
        Integer code = native2Code.get(ntv);
        return code == null ? NONE_CODE : code;
    }
}
