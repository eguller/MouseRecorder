package com.eguller.mouserecorder.ui.util;

import java.awt.*;
import java.net.URL;

/**
 * User: eguller
 * Date: 3/18/14
 * Time: 6:36 AM
 */
public class UrlOpener {
    public static void open(String url) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URL(url).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
