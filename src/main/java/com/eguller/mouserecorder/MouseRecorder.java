package com.eguller.mouserecorder;

import com.eguller.mouserecorder.player.PlayerImpl;
import com.eguller.mouserecorder.player.api.Player;
import com.eguller.mouserecorder.recorder.BaseRecorder;
import com.eguller.mouserecorder.recorder.MacRecorder;
import com.eguller.mouserecorder.recorder.api.Recorder;
import com.eguller.mouserecorder.ui.MainWindow;
import com.eguller.mouserecorder.ui.util.MessageBox;
import com.eguller.mouserecorder.util.OS;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Initializes frame
 */
public class MouseRecorder
{

    public static void main( String[] args ) {
        setLookAndFeel();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = null;
                try {
                    frame = new MainWindow(createRecorder(), createPlayer());
                } catch (AWTException e) {
                    MessageBox.showError("Player", "Creating player failed.");
                    System.exit(-1);
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    private static Recorder createRecorder(){
        if(OS.isMacOSX()){
            return new MacRecorder();
        } else {
            return new BaseRecorder();
        }
    }

    private static Player createPlayer() throws AWTException {
        return new PlayerImpl();
    }

    public static void setLookAndFeel(){
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
    }
}
