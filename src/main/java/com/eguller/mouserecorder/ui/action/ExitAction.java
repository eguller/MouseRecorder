package com.eguller.mouserecorder.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: eguller
 * Date: 2/23/14
 * Time: 12:47 AM
 */
public class ExitAction implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
