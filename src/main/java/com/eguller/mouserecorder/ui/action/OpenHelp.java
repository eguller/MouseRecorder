package com.eguller.mouserecorder.ui.action;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * User: eguller
 * Date: 3/17/14
 * Time: 12:59 AM
 */
public class OpenHelp implements ActionListener {
    private static String helpUrl = "https://github.com/eguller/MouseRecorder#mouserecorder";

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URL(helpUrl).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
