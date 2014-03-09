package com.eguller.mouserecorder.ui.action;

import com.eguller.mouserecorder.config.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: eguller
 * Date: 3/9/14
 * Time: 11:17 PM
 */
public class MinimizeOnRecordAction extends BooleanMenuAction {
    public MinimizeOnRecordAction(Config config) {
        super(config);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JCheckBoxMenuItem source = (JCheckBoxMenuItem) actionEvent.getSource();
        if (source.isSelected()) {
            config.activateMinimizeOnRecord();
        } else {
            config.deactivateMinimizeOnRecord();
        }
    }
}
