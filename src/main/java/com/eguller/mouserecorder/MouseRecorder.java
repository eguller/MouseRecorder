package com.eguller.mouserecorder;

import com.eguller.mouserecorder.ui.MainWindow;

import javax.swing.*;

/**
 *
 * Initializes frame
 */
public class MouseRecorder
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MainWindow();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
