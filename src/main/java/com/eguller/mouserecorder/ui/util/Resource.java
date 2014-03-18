package com.eguller.mouserecorder.ui.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * User: eguller
 * Date: 3/18/14
 * Time: 7:05 AM
 */
public class Resource {
    public static String getResourceAsText(String resource) {
        StringBuilder sb = new StringBuilder();
        InputStream is = ClassLoader.getSystemResourceAsStream(resource);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public static String getTemplatedResource(String resource, Map<String, Object> templateValues) {
        String text = getResourceAsText(resource);
        for (String key : templateValues.keySet()) {
            text = text.replaceAll("\\$\\{" + key + "\\}", templateValues.get(key).toString());
        }
        return text;
    }
}
