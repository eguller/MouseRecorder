package com.eguller.mouserecorder.ui.action;

import com.eguller.mouserecorder.config.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: eguller
 * Date: 3/9/14
 * Time: 11:18 PM
 */
public class MinimizeOnPlayAction extends BooleanMenuAction {

    public MinimizeOnPlayAction(Config config) {
        super(config);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) actionEvent.getSource();
        if (menuItem.isSelected()) {
            config.activateMinimizeOnPlay();
        } else {
            config.deactivateMinimizeOnPlay();
        }
    }
}
