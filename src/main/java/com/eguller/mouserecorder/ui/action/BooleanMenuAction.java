package com.eguller.mouserecorder.ui.action;

import com.eguller.mouserecorder.config.Config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: eguller
 * Date: 3/9/14
 * Time: 11:15 PM
 */
public abstract class BooleanMenuAction implements ActionListener {
    Config config;

    public BooleanMenuAction(Config config) {
        this.config = config;
    }

    @Override
    public abstract void actionPerformed(ActionEvent actionEvent);
}
