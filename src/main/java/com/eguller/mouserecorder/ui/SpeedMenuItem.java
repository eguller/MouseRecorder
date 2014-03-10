package com.eguller.mouserecorder.ui;

import com.eguller.mouserecorder.config.Config;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

/**
 * User: eguller
 * Date: 3/10/14
 * Time: 7:19 AM
 */
public class SpeedMenuItem extends JPanel {
    private static final int SPEED_FACTOR = 8;
    private static final int MIN = -4;
    private static final int MAX = 4;
    JLabel speedLabel;
    JSlider speedSlider;

    Config config;

    public SpeedMenuItem(Config config) {
        this.config = config;
        speedLabel = new JLabel("Speed");
        speedSlider = new JSlider(MIN, MAX);
        speedSlider.putClientProperty("JComponent.sizeVariant", "small");
        speedLabel.setLabelFor(speedSlider);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(-4, new JLabel("Slow"));
        labelTable.put(-3, new JLabel(""));
        labelTable.put(-2, new JLabel(""));
        labelTable.put(-1, new JLabel(""));
        labelTable.put(0, new JLabel("Normal"));
        labelTable.put(1, new JLabel(""));
        labelTable.put(2, new JLabel(""));
        labelTable.put(3, new JLabel(""));
        labelTable.put(4, new JLabel("Fast"));
        speedSlider.setLabelTable(labelTable);
        speedSlider.setPaintTicks(true);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintLabels(true);
        speedSlider.setPaintTrack(true);

        speedLabel.setBorder(new EmptyBorder(0, 5, 0, 0));

        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {

            }
        });

        this.setLayout(new BorderLayout());
        this.add(speedLabel, BorderLayout.NORTH);
        this.add(speedSlider, BorderLayout.CENTER);


    }

    private static int config2SliderSpeed(double configSpeed) {
        return (int) Math.round(configSpeed * SPEED_FACTOR);
    }
}
