package com.eguller.mouserecorder.ui;

import javax.swing.*;

/**
 * User: eguller
 * Date: 3/14/14
 * Time: 7:43 AM
 */
public class AboutDialog {
    JComponent parent;

    AboutDialog(JComponent parent) {
        this.parent = parent;
    }

    public void display() {
        JEditorPane editor = new JEditorPane();
        editor.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        editor.setEditable(false);

    }


}
