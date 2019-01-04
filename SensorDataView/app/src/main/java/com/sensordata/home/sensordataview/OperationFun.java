package com.sensordata.home.sensordataview;


public class OperationFun {
    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 2000) {
            System.out.println("连按");
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
