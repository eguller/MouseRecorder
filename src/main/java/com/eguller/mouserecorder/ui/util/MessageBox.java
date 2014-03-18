package com.eguller.mouserecorder.ui.util;

import javax.swing.*;
import java.awt.*;

/**
 * User: eguller
 * Date: 2/21/14
 * Time: 11:59 PM
 */
public class MessageBox {
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
    }

    public static void showError(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showError(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
