package com.eguller.mouserecorder.util;

/**
 * User: eguller
 * Date: 12/15/13
 * Time: 11:38 PM
 * Modified from org.jdesktop.swingx.util.OS
 */
public class OS {
    private static final boolean osIsMacOsX;
    private static final boolean osIsWindows;
    private static final boolean osIsLinux;

    static {
        String os = System.getProperty("os.name");
        if (os != null)
            os = os.toLowerCase();

        osIsMacOsX = "mac os x".equals(os);
        osIsWindows = os != null && os.indexOf("windows") != -1;
        osIsLinux = os != null && os.indexOf("linux") != -1;
    }

    /**
     * @return true if this VM is running on Mac OS X
     */
    public static boolean isMacOSX() {
        return osIsMacOsX;
    }

    /**
     * @return true if this VM is running on Windows
     */
    public static boolean isWindows() {
        return osIsWindows;
    }

    /**
     * @return true if this VM is running on a Linux distribution
     */
    public static boolean isLinux() {
        return osIsLinux;
    }



}
