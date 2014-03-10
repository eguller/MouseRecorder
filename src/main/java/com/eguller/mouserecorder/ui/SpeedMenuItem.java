package com.eguller.mouserecorder.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * User: eguller
 * Date: 3/10/14
 * Time: 7:19 AM
 */
public class SpeedMenuItem extends JPanel {
    JLabel speedLabel;
    JSlider speedSlider;

    public SpeedMenuItem() {
        speedLabel = new JLabel("Speed");
        speedSlider = new JSlider();
        speedLabel.setLabelFor(speedSlider);
        speedLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
        this.setLayout(new BorderLayout());

        this.add(speedLabel, BorderLayout.NORTH);
        this.add(speedSlider, BorderLayout.CENTER);


    }
}
