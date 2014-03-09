package com.eguller.mouserecorder.ui.action;

import com.eguller.mouserecorder.format.api.Format;
import com.eguller.mouserecorder.ui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: eguller
 * Date: 2/26/14
 * Time: 7:48 AM
 */
public class SaveFileAction implements ActionListener{
    final JFileChooser fc = new JFileChooser();
    MainWindow mainWindow;
    Format format;

    public SaveFileAction(MainWindow mainWindow, Format format) {
        this.mainWindow = mainWindow;
        this.format = format;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int retrieval = fc.showSaveDialog(mainWindow);
        if (retrieval == JFileChooser.APPROVE_OPTION) {
            try {
                format.save(fc.getSelectedFile(), mainWindow.getRecord());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
