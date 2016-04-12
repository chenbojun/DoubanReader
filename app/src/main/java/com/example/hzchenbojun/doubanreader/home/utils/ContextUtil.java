package com.example.hzchenbojun.doubanreader.home.utils;

/**
 * Created by hzchenbojun on 2016/3/11.
 */
import android.app.Application;

public class ContextUtil extends Application {
    private static ContextUtil instance;

    public static ContextUtil getInstance() {
        if(instance == null) {
            instance = new ContextUtil();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }
}
