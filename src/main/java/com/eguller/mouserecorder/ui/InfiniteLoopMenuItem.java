package com.eguller.mouserecorder.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * User: eguller
 * Date: 3/11/14
 * Time: 6:40 AM
 */
public class InfiniteLoopMenuItem extends JPanel {
    JCheckBox infiniteLoopCB;

    public InfiniteLoopMenuItem() {
        this.infiniteLoopCB = new JCheckBox("Infinite Loop");
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(infiniteLoopCB);
    }

    public void addActionListener(ActionListener actionListener) {
        infiniteLoopCB.addActionListener(actionListener);
    }

    public void setSelected(boolean selected) {
        infiniteLoopCB.setSelected(selected);
    }
}
