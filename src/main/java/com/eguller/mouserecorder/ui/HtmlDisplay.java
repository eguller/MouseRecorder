package com.eguller.mouserecorder.ui;

import com.eguller.mouserecorder.ui.util.UrlOpener;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;

/**
 * User: eguller
 * Date: 3/14/14
 * Time: 7:43 AM
 */
public class HtmlDisplay {
    public static final String TEXT_HTML = "text/html";
    JEditorPane editorPane;

    public HtmlDisplay(String html) {
        editorPane = new JEditorPane(TEXT_HTML, html);
        display();
    }

    public HtmlDisplay() {
        editorPane = new JEditorPane();
        editorPane.setContentType(TEXT_HTML);
        display();
    }

    public Component getComponent() {
        return editorPane;
    }

    public void display() {
        editorPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(final HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ENTERED) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            // TIP: Show hand cursor
                            SwingUtilities.getWindowAncestor(editorPane).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            // TIP: Show URL as the tooltip
                            editorPane.setToolTipText(e.getURL().toExternalForm());
                        }
                    });
                } else if (e.getEventType() == HyperlinkEvent.EventType.EXITED) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            // Show default cursor
                            SwingUtilities.getWindowAncestor(editorPane).setCursor(Cursor.getDefaultCursor());

                            // Reset tooltip
                            editorPane.setToolTipText(null);
                        }
                    });
                } else if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    // TIP: Starting with JDK6 you can show the URL in desktop browser
                    UrlOpener.open(e.getURL().toString());
                    //System.out.println("Go to URL: " + e.getURL());
                }
            }
        });

        editorPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        editorPane.setEditable(false);
        editorPane.setBackground(new JLabel().getBackground());
    }
}
