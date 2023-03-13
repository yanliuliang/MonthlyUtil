package com.example.myapplication;

import android.app.Application;

import org.xutils.x;

public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //xUtils3初始化
        x.Ext.init(this);
        //是否输出debug日志
        x.Ext.setDebug(BuildConfig.DEBUG);
        //数据库配置
        Xutils.initDbConfiginit();

    }


}
