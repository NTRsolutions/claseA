package com.apreciasoft.mobile.remisclasea.Util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ServicesSleep extends Service {

    public static Tiempo tiempo = new Tiempo();
    public static  boolean mRunning = false;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        tiempo.Contar(getApplicationContext());
        if (!mRunning) {
            mRunning = true;
            // do something
        }
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopping the player when service is destroyed
        tiempo.Detener();
        mRunning = false;

    }



}