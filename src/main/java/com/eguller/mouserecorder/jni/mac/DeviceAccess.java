package com.eguller.mouserecorder.jni.mac;

import com.eguller.mouserecorder.exceptions.AccessibilityApiCannotBeEnabledException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: eguller
 * Date: 2/21/14
 * Time: 9:46 PM
 */
public class DeviceAccess {
    private static final String ACCESSIBILITY_API_FILE = "/var/db/.AccessibilityAPIEnabled";
    static {
        loadLibrary();
    }
    public static void enableIfDisabled(){
        if(!isEnabled()){
            enable();
            if(!isEnabled()){
                throw new AccessibilityApiCannotBeEnabledException();
            }
        }
    }

    private static boolean isEnabled(){
        File file  = new File(ACCESSIBILITY_API_FILE);
        return file.exists();
    }
    private static native int enable();
    private static void loadLibrary(){
        String libraryName = "DeviceAccess";
        String libResourcePath = "/com/eguller/mouserecorder/jni/mac/";
        String libNativeName = System.mapLibraryName(libraryName);
        libNativeName = libNativeName.replaceAll("\\.jnilib$", "\\.dylib");
        try{
            File libFile = File.createTempFile("libDeviceAccess", "dylib", null);

            InputStream libInputStream =
                    DeviceAccess.class.getResourceAsStream(
                            libResourcePath.toLowerCase()
                                    + libNativeName
                    );

            if (libInputStream == null) {
                throw new IOException("Unable to locate the native library.");
            }


            FileOutputStream libOutputStream = new FileOutputStream(libFile);
            byte[] buffer = new byte[4 * 1024];

            int size;
            while ((size = libInputStream.read(buffer)) != -1) {
                libOutputStream.write(buffer, 0, size);
            }
            libOutputStream.close();
            libInputStream.close();

            libFile.deleteOnExit();

            System.load(libFile.getPath());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static void main(String args[]){
        int result = DeviceAccess.enable();
        System.out.println(result);
    }
}
