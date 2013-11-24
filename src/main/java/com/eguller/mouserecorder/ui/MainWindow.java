package com.eguller.mouserecorder.ui;

import com.eguller.mouserecorder.recorder.Player;
import com.eguller.mouserecorder.recorder.Recorder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * User: eguller
 * Date: 11/24/13
 * Time: 3:28 PM
 */
public class MainWindow extends JFrame implements Observer {
    JMenuBar menuBar;
    JButton playButton;
    JButton recordButton;



    JMenu fileMenu;
    JMenuItem saveItem;
    JMenuItem openItem;
    JSeparator separator;
    JMenuItem exitItem;

    JMenu aboutMenu;
    JMenu aboutItem;
    JMenu helpItem;

    JLabel statusBar;

    Image startImage;
    Image recordImage;
    Image stopImage;

    JPanel buttonPanel;

    public MainWindow(){
        addComponentstoPane(getContentPane());
        this.setResizable(false);
    }

    private  void addComponentstoPane(Container container){
        loadImages();
        playButton = new JButton(new ImageIcon(startImage));
        playButton.setEnabled(false);
        playButton.setName("Play");
        playButton.addActionListener(new PlayAction());

        recordButton = new JButton(new ImageIcon(recordImage));
        recordButton.setName(RecordButtonState.RECORD.toString());
        recordButton.addActionListener(new RecordAction());

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        saveItem = new JMenuItem("Save");
        openItem = new JMenuItem("Open");
        separator = new JSeparator();
        exitItem = new JMenuItem("Exit");
        fileMenu.add(saveItem);
        fileMenu.add(openItem);
        fileMenu.add(separator);
        fileMenu.add(exitItem);

        aboutMenu = new JMenu("About");
        aboutItem = new JMenu("About");
        helpItem = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        statusBar  = new JLabel("Click red button to start recording.");
        statusBar.setFont(statusBar.getFont().deriveFont(10.0f));
        statusBar.setHorizontalAlignment(SwingConstants.RIGHT);

        buttonPanel = new JPanel();
        buttonPanel.add(playButton, BorderLayout.WEST);
        buttonPanel.add(recordButton, BorderLayout.EAST);
        buttonPanel.setBorder(new EmptyBorder(3,10,3,10));

        container.add(menuBar, BorderLayout.PAGE_START);
        container.add(buttonPanel, BorderLayout.CENTER);
        container.add(statusBar, BorderLayout.PAGE_END);
        setResizable(false);
        setSize(512, 512);
        setTitle("Mouse Recorder");
    }

    public void loadImages(){
        try {
            startImage = ImageIO.read(getClass().getResourceAsStream("/start.png"));
            recordImage = ImageIO.read(getClass().getResourceAsStream("/record.png"));
            stopImage = ImageIO.read(getClass().getResourceAsStream("/stop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Observable observable, Object o) {
        statusBar.setText("Click blue to replay/ red to record");
        playButton.setEnabled(true);
        recordButton.setEnabled(true);
        recordButton.setName(RecordButtonState.POSTREPLAY.toString());

    }

    /**
     * Record button action listener.
     */
    public class RecordAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton recordButton = (JButton)actionEvent.getSource();
            //if button at record state switch to stop state
            if(recordButton.getName().equals(RecordButtonState.RECORD.toString())){
                playButton.setEnabled(false);
                recordButton.setIcon(new ImageIcon(stopImage));
                recordButton.setName(RecordButtonState.STOP.toString());
                statusBar.setText("Click green button to stop recording...");
                Recorder.start();
            }
            //if button and stop state switch to record state
            else if(recordButton.getName().equals(RecordButtonState.STOP.toString())){
                Recorder.stop();
                playButton.setEnabled(true);
                recordButton.setIcon(new ImageIcon(recordImage));
                recordButton.setName(RecordButtonState.RECORD.toString());
                statusBar.setText("Click red button to start recording");

            }
            //This state is introduced to prevent endless record loop.
            else if(recordButton.getName().equals(RecordButtonState.POSTREPLAY.toString())){
                playButton.setName(RecordButtonState.RECORD.toString());
            }
        }
    }

    public class PlayAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                recordButton.setEnabled(false);
                playButton.setEnabled(false);
                Player player = new Player(Recorder.getRecordedEvents());
                player.addObserver(MainWindow.this);
                SwingUtilities.invokeLater(player);
            } catch (AWTException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
