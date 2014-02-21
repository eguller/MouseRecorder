//
//  DeviceAccess.c
//  DeviceAccess
//
//  Created by Engin Guller on 2/21/14.
//  Copyright (c) 2014 Engin Guller. All rights reserved.
//

#include "DeviceAccess.h"
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <Security/Authorization.h>
#include <Security/AuthorizationTags.h>

JNIEXPORT jint JNICALL
Java_com_eguller_mouserecorder_jni_mac_DeviceAccess_enable (JNIEnv *env, jclass clazz){
    OSStatus myStatus;
    AuthorizationFlags myFlags = kAuthorizationFlagDefaults;
    AuthorizationRef myAuthorizationRef;
    
    myStatus = AuthorizationCreate(NULL, kAuthorizationEmptyEnvironment,
                                   myFlags, &myAuthorizationRef);
    if (myStatus != errAuthorizationSuccess)
        return myStatus;
    
    {
        
        
        AuthorizationItem dialogConfiguration[2];
        const char *message = "Mouse Recorder needs to active 'Device Access' once to record mouse actions.";
        dialogConfiguration[0].name = kAuthorizationEnvironmentPrompt;
        dialogConfiguration[0].value = message;
        dialogConfiguration[0].valueLength = StrLength(message);
        
        
        AuthorizationItem authItem = {kAuthorizationRightExecute, 0, 0};
        AuthorizationRights myRights = {1,&authItem};
        
        AuthorizationEnvironment authorizationEnvironment = { 0 };
        authorizationEnvironment.items = dialogConfiguration;
        authorizationEnvironment.count = 1;
        
        
        myFlags = kAuthorizationFlagInteractionAllowed | kAuthorizationFlagExtendRights;
        myStatus = AuthorizationCopyRights (myAuthorizationRef, &myRights, &authorizationEnvironment, myFlags, NULL );
    }
    
    if (myStatus != errAuthorizationSuccess) goto DoneWorking;
    
    {
        char myToolPath[] = "/usr/bin/touch";
        char *myArguments[] = { "/var/db/.AccessibilityAPIEnabled", NULL };
        FILE *myCommunicationsPipe = NULL;
        char myReadBuffer[128];
        
        myFlags = kAuthorizationFlagDefaults;
        myStatus = AuthorizationExecuteWithPrivileges
        (myAuthorizationRef, myToolPath, myFlags, myArguments,
         &myCommunicationsPipe);
        
        if (myStatus == errAuthorizationSuccess)
            for(;;)
            {
                int bytesRead = read (fileno (myCommunicationsPipe),
                                      myReadBuffer, sizeof (myReadBuffer));
                if (bytesRead < 1) goto DoneWorking;
                write (fileno (stdout), myReadBuffer, bytesRead);
            }
    }
    
DoneWorking:
    
    AuthorizationFree (myAuthorizationRef, kAuthorizationFlagDefaults);
    return myStatus;
}
