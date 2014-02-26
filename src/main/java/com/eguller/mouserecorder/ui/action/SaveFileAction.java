package com.eguller.mouserecorder.ui.action;

import com.eguller.mouserecorder.format.DefaultFormat;
import com.eguller.mouserecorder.format.Format;
import com.eguller.mouserecorder.recorder.Record;
import com.eguller.mouserecorder.ui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

/**
 * User: eguller
 * Date: 2/26/14
 * Time: 7:48 AM
 */
public class SaveFileAction implements ActionListener{
    final JFileChooser fc = new JFileChooser();
    MainWindow mainWindow;
    Record record;
    Format format = new DefaultFormat();
    public SaveFileAction(MainWindow mainWindow){
        this.mainWindow = mainWindow;

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
