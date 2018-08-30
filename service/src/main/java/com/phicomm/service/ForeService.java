package com.phicomm.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class ForeService extends Service {
    public ForeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        beginForeService();
    }

    private void beginForeService() {
        Intent intent = new Intent();
        intent.setClassName("com.phicomm.rxjava", "com.phicomm.rxjava.launchmode.FirstActivity");
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Content title")
                .setContentText("Content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        startForeground(1, notification);

    }
}
