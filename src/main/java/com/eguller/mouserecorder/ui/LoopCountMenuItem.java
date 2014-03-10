package com.eguller.mouserecorder.ui;

import com.eguller.mouserecorder.config.Config;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * User: eguller
 * Date: 3/10/14
 * Time: 6:42 AM
 */
public class LoopCountMenuItem extends JPanel {
    private static final int SPINNER_WIDTH = 64;
    private static final int SPINNER_HEIGHT = 22;
    JLabel loopCountLabel;
    JSpinner loopCountSpinner;
    final Config config;

    public LoopCountMenuItem(final Config config) {
        this.config = config;
        loopCountLabel = new JLabel("Loop Count");
        loopCountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        loopCountLabel.setLabelFor(loopCountSpinner);
        loopCountSpinner.setValue(config.getLoopCount());

        loopCountSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                config.setLoopCount((Integer) (loopCountSpinner.getValue()));
            }
        });
        loopCountSpinner.setPreferredSize(new Dimension(SPINNER_WIDTH, SPINNER_HEIGHT));
        this.add(loopCountLabel);
        this.add(loopCountSpinner);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }
}
