package com.mtianyan.mtianyan001.application;

import android.app.Application;

import com.mtianyan.mtianyan001.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.application
 * 文件名：BaseApplication
 * 作者：mtianyan
 * 创建时间：2017/5/30 15:24
 * 描述：Application
 */
public class BaseApplication extends Application {
    public static BaseApplication mInstance;
    // 创建
    @Override
    public void onCreate() {

        super.onCreate();
        //初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);

        //第一：默认初始化bmob
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");

    }
    public static BaseApplication getInstance()
    {

        return mInstance;
    }

}
