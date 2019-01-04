package com.sensordata.home.sensordataview;


import android.app.Application;

public class Data extends Application {
    private String userName;        //当前登录用户名

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String c) {
        this.userName = c;
    }

    @Override
    public void onCreate() {
        userName = "";
        super.onCreate();
    }

}

