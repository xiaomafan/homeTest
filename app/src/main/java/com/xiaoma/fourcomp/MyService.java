package com.xiaoma.fourcomp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.xiaoma.hometest.LogHelp;
import com.xiaoma.hometest.R;

/**
 * Created by xiaoma on 2017/4/5.
 */

public class MyService extends Service {

    private final static String Tag = "MyService";

    private MyBinder mBinder = new MyBinder();

    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private Notification notification;

    @Override
    public void onCreate() {
        super.onCreate();
        LogHelp.e(Tag, "onCreate()");
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        showNotification();
        LogHelp.e(Tag, "MyService=" + Thread.currentThread().getId() + "/" + Thread.currentThread().getName());
        
        //如果在service里添加睡眠60s,那么会出现阻塞，会出现ANR
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private void showNotification() {
        builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("社员汇正在下载")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())//设置通知时间  
                .setPriority(Notification.PRIORITY_HIGH)//高优先级  
                .setVisibility(Notification.VISIBILITY_PUBLIC);
        notification = builder.build();
        notificationManager.notify(1, notification);
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogHelp.e(Tag, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogHelp.e(Tag, "IBinder()");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        LogHelp.e(Tag, "onDestroy()");
        super.onDestroy();
    }


    class MyBinder extends Binder {

        public void startDown() {
            LogHelp.e(Tag, "startDown()");
        }
    }
}
