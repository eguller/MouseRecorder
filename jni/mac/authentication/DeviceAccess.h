//
//  DeviceAccess.h
//  DeviceAccess
//
//  Created by Engin Guller on 2/21/14.
//  Copyright (c) 2014 Engin Guller. All rights reserved.
//

#ifndef _Included_com_eguller_mouserecorder_jni_mac_DeviceAccess
#define _Included_com_eguller_mouserecorder_jni_mac_DeviceAccess
#include <jni.h>
#ifdef __cplusplus
extern "C" {
#endif
    JNIEXPORT jint JNICALL
        Java_com_eguller_mouserecorder_jni_mac_DeviceAccess_enable
            (JNIEnv *, jclass);
#endif
#ifdef __cplusplus
}
#endif
