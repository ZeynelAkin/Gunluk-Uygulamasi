package com.example.user.gunluk;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationHatırlatma extends Application {

    public static final String Channel1="channel1";
    
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();

    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel1=new NotificationChannel(
                    Channel1,"Channel 1",NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Hatırlatma");

            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }

    }
}
