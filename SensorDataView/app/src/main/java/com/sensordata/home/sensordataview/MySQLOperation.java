package com.sensordata.home.sensordataview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class MySQLOperation {
    public static void userRegisterSQL(final Handler handler, final String userName, final String password){
        new Thread(){
            @Override
            public void run() {
                super.run();

                String ret = HttpRequest.sendGet("http://www.jiamilai.top:8809/register", "userName="+userName+"&password="+password);
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("state",ret);
                msg.setData(data);
                handler.sendMessage(msg);
                //System.out.println("注册函数运行中");
            }
        }.start();
    }
    public static void userLoginSQL(final Handler handler,final String userName,final String password){
        new Thread(){
            @Override
            public void run() {
                super.run();

                String ret = HttpRequest.sendGet("http://www.jiamilai.top:8809/login", "userName="+userName+"&password="+password);
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("state",ret);
                msg.setData(data);
                handler.sendMessage(msg);
                System.out.println("登录函数运行中");
            }
        }.start();
    }
    public static void userDevAdd(final Handler handler,final String userName,final String devName,final String devMAC){
        new Thread(){
            @Override
            public void run() {
                super.run();
                String ret = HttpRequest.sendGet("http://www.jiamilai.top:8809/addDev", "userName="+userName+"&devName="+devName+"&devMAC="+devMAC);
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("state",ret);
                msg.setData(data);
                handler.sendMessage(msg);
                System.out.println("添加设备函数运行中");
            }
        }.start();
    }
}