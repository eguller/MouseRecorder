package com.eguller.mouserecorder.ui.action;

import com.eguller.mouserecorder.format.api.Format;
import com.eguller.mouserecorder.recorder.Record;
import com.eguller.mouserecorder.ui.MainWindow;
import com.eguller.mouserecorder.ui.state.ButtonStates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * User: eguller
 * Date: 3/6/14
 * Time: 7:42 AM
 */
public class OpenFileAction implements ActionListener {
    MainWindow mainWindow = null;
    Format format;

    public OpenFileAction(MainWindow mainWindow, Format format) {
        this.mainWindow = mainWindow;
        this.format = format;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser();
        int retVal = jFileChooser.showOpenDialog(mainWindow);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            Record record = format.load(file);
            mainWindow.setRecord(record);
            ButtonStates.POSTPLAY.apply(mainWindow);
        }
    }
}
