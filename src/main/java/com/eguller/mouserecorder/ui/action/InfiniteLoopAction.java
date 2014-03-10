package com.eguller.mouserecorder.ui.action;

import com.eguller.mouserecorder.config.Config;
import com.eguller.mouserecorder.ui.LoopCountMenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: eguller
 * Date: 3/9/14
 * Time: 11:50 PM
 */
public class InfiniteLoopAction extends BooleanMenuAction {

    LoopCountMenuItem loopCountMenuItem;

    public InfiniteLoopAction(Config config, LoopCountMenuItem loopCountMenuItem) {
        super(config);
        this.loopCountMenuItem = loopCountMenuItem;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JCheckBoxMenuItem infiniteLoopMenuItem = (JCheckBoxMenuItem) actionEvent.getSource();
        if (infiniteLoopMenuItem.isSelected()) {
            config.activeInfiniteLoop();
            loopCountMenuItem.setVisible(false);
        } else {
            config.deactivateInfiniteLoop();
            loopCountMenuItem.setVisible(true);
        }
    }
}
