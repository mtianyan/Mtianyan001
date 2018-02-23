package com.mtianyan.mtianyan001.utils;

import android.util.Log;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.utils
 * 文件名：L
 * 作者：mtianyan
 * 创建时间：2017/6/1 12:14
 * 描述：LOG封装
 */
public class L {
    //开关
    public static final boolean DEBUG = true;
    //TAG
    public static final String TAG = "mtianyan001";

    //实现五个等级 D I W E F

    public static void d(String test) {
        if (DEBUG) {
            Log.d(TAG, test);
        }
    }
    public static void i(String test){
        if(DEBUG){
            Log.i(TAG,test);
        }
    } public static void w(String test){
        if(DEBUG){
            Log.w(TAG,test);
        }
    }public static void e(String test){
        if(DEBUG){
            Log.e(TAG,test);
        }
    }public static void f(String test){
        if(DEBUG){
            Log.wtf(TAG,test);
        }
    }
}
