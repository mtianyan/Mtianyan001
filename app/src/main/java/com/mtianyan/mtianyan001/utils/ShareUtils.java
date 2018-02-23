package com.mtianyan.mtianyan001.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.utils
 * 文件名：ShareUtils
 * 作者：mtianyan
 * 创建时间：2017/6/1 15:42
 * 描述：封装SharePreferences
 */
public class ShareUtils {
    //原始方法存取
//    private  void test(Context mcontext){
//        SharedPreferences sp = mcontext.getSharedPreferences("config",Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor =sp.edit();
//        sp.getString("key","未获取到值");
//
//        editor.putString("key","value");
//
//        editor.commit();
//
//
//    }
    public static final String NAME = "config";

    //键 值
    public static  void putString(Context mcontext,String key,String value){
        SharedPreferences sp =mcontext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    //键 默认值
    public static String getString(Context mcontext,String key,String defvalue){
        SharedPreferences sp = mcontext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getString(key,defvalue);

    } //键 值
    public static  void putInt(Context mcontext,String key,int value){
        SharedPreferences sp =mcontext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }
    //键 默认值
    public static int getInt(Context mcontext,String key,int defvalue){
        SharedPreferences sp = mcontext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getInt(key,defvalue);

    } //键 值
    public static  void putBoolean(Context mcontext,String key,boolean value){
        SharedPreferences sp =mcontext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
    //键 默认值
    public static Boolean getBoolean(Context mcontext,String key,boolean defvalue){
        SharedPreferences sp = mcontext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defvalue);

    }

    //删除单个
    public static void deleShare(Context mcontext,String key){
        SharedPreferences sp = mcontext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    //删除全部
    public static void deleAll(Context mcontext){
        SharedPreferences sp = mcontext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

}
