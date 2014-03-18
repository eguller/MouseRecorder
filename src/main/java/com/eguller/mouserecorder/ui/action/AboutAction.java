package com.eguller.mouserecorder.ui.action;

import com.eguller.mouserecorder.ui.HtmlDisplay;
import com.eguller.mouserecorder.ui.util.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * User: eguller
 * Date: 3/18/14
 * Time: 6:55 AM
 */
public class AboutAction implements ActionListener {
    private static final String resource = "com/eguller/mouserecorder/about/html/about.html";
    Component parent;
    String message;
    Icon icon;

    public AboutAction(Component parent) {
        this.parent = parent;
        Map<String, Object> templateValues = new HashMap<String, Object>();
        String version = getVersion();
        templateValues.put("version", version);
        message = Resource.getTemplatedResource(resource, templateValues);
        icon = new ImageIcon(ClassLoader.getSystemResource("start.png"));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Component component = new HtmlDisplay(message).getComponent();
        JScrollPane jScrollPane = new JScrollPane(component);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        JOptionPane.showMessageDialog(parent, jScrollPane, "About", JOptionPane.PLAIN_MESSAGE, icon);
    }

    private static String getVersion() {
        String version = "X-Snapshot";
        Class clazz = AboutAction.class;
        String className = clazz.getSimpleName() + ".class";
        String classPath = clazz.getResource(className).toString();
        if (!classPath.startsWith("jar")) {
            // Class not from JAR
            return version;
        }
        String manifestPath = classPath.substring(0, classPath.lastIndexOf("!") + 1) +
                "/META-INF/MANIFEST.MF";
        Manifest manifest = null;
        try {
            manifest = new Manifest(new URL(manifestPath).openStream());
            Attributes attr = manifest.getMainAttributes();
            version = attr.getValue("Version");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return version;
    }
}
