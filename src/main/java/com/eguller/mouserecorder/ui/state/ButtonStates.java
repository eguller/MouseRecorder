package com.eguller.mouserecorder.ui.state;

import com.eguller.mouserecorder.ui.MainWindow;

/**
 * User: eguller
 * Date: 12/3/13
 * Time: 12:11 AM
 */


public enum ButtonStates implements Apply {

    PRERECORD {
        public void apply(MainWindow mainWindow) {
            mainWindow.getPlayButton().setEnabled(false);
            mainWindow.getRecordButton().setEnabled(true);
            mainWindow.getStopButton().setEnabled(false);
            mainWindow.getStatusBar().setText("Click red button to start recording.");
        }
    },
    RECORDING {
        public void apply(MainWindow mainWindow) {
            mainWindow.getStopButton().setEnabled(true);
            mainWindow.getRecordButton().setEnabled(false);
            mainWindow.getPlayButton().setEnabled(false);
            mainWindow.getStatusBar().setText("Click green button to stop recording.");
        }
    }

    , POSTRECORD {
        public void apply(MainWindow mainWindow) {
            mainWindow.getStopButton().setEnabled(false);
            mainWindow.getRecordButton().setEnabled(true);
            mainWindow.getPlayButton().setEnabled(true);
            mainWindow.getStatusBar().setText("Click blue button to play.");
        }
    },
    POSTPLAY{
        public void apply(MainWindow mainWindow) {
            mainWindow.getStopButton().setEnabled(false);
            mainWindow.getPlayButton().setEnabled(true);
            mainWindow.getRecordButton().setEnabled(true);
            mainWindow.getStatusBar().setText("Click blue to play / red to record.");
        }
    } ,
    PLAYING {
        public void apply(MainWindow mainWindow) {
            mainWindow.getPlayButton().setEnabled(false);
            mainWindow.getRecordButton().setEnabled(false);
            mainWindow.getStopButton().setEnabled(false);
            mainWindow.getStatusBar().setText("Playing your mouse / keyboard actions.");
        }
    };


    public void apply(MainWindow mainWindow){}

}
