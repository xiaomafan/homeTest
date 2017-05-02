//
// Created by xiaoma on 2017/5/2.
//

#include "com_sheyuan_jnidemo_JniUtils.h"


JNIEXPORT jstring JNICALL Java_com_sheyuan_jnidemo_JniUtils_getStringFormC
        (JNIEnv *env, jobject obj)
{
    return (*env)->NewStringUTF(env,"This just a test for Android Studio NDK JNI developer!");
}
