package com.sheyuan.jnidemo;

/**
 * Created by xiaoma on 2017/5/2.
 */

public class JniUtils {
    static {
        System.loadLibrary("NdkJniDemo");//之前在build.gradle里面设置的so名字，必须一致
        }
    
    public  native String getStringFormC();
}
