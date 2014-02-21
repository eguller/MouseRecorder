package com.eguller.mouserecorder.exceptions;

/**
 * User: eguller
 * Date: 2/21/14
 * Time: 11:27 PM
 */
public class AccessibilityApiCannotBeEnabledException extends RuntimeException {
    private static final String MESSAGE = "'Device Accessibility' cannot be enabled.\n" +
            "Please go to System Preferences -> Accessibility and \n" +
            "Check 'Enable access for assistive devices' option.";
    public AccessibilityApiCannotBeEnabledException(){
        super(MESSAGE);
    }
}
