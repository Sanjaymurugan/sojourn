package com.example.sanjaymurugan.sojournhappy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import java.util.Random;

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;

/**
 * Created by sanjay murugan on 27-03-2018.
 */

public class broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.applogo)
                .setContentTitle("Sojourn")
                .setContentText("You have entered into the dangerous area!");
        NotificationManager manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder.setSound(Settings.System.DEFAULT_RINGTONE_URI);
        Uri not= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r=RingtoneManager.getRingtone(context,not);
        r.play();
        PendingIntent contentIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(contentIntent);
        builder.setVisibility(VISIBILITY_PUBLIC);
        Notification notification=builder.build();
//        builder.setVibrate(new long[] {1000,1000,1000,1000,1000});
//        notification.category|=Notification.CATEGORY_SERVICE;
//        notification.flags|=Notification.FLAG_SHOW_LIGHTS;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_LIGHTS;

//        notification.defaults |= Notification.DEFAULT_VIBRATE;
        manager.notify(new Random().nextInt(),notification);
    }
}
