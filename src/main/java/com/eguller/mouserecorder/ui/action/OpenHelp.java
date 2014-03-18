package com.eguller.mouserecorder.ui.action;

import com.eguller.mouserecorder.ui.util.UrlOpener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: eguller
 * Date: 3/17/14
 * Time: 12:59 AM
 */
public class OpenHelp implements ActionListener {
    private static String helpUrl = "https://github.com/eguller/MouseRecorder#mouserecorder";

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        UrlOpener.open(helpUrl);
    }
}
