package com.example.sanjaymurugan.sojournhappy;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;
import static com.example.sanjaymurugan.sojournhappy.allimpplaces.id;
import static com.example.sanjaymurugan.sojournhappy.allimpplaces.src;

/**
 * Created by sanjay murugan on 30-03-2018.
 */

public class notificationservice extends Service {
    allimpplaces obj=new allimpplaces();
    List<LatLng> list1,list2,list3,list4,list5,list6,list7;
    double[] latituderestrictedplacemalampuzha= {10.880595, 10.905227, 10.889213,10.863000};
    double[] longituderestrictedplacemalampuzha={76.665895, 76.672396, 76.720805,76.719689};
    double[] latres1={10.855666,10.837711,10.834844};
    double[] lonres1={76.651025,76.670594,76.657119};
    double[] latres2={10.958774,10.941415,10.950011};
    double[] lonres2={76.673546,76.667452,76.692772};
    double[] latres3={10.925825,10.933578,10.920094};
    double[] lonres3={76.682215,76.707363,76.701184};
    double[] latres4={10.842329,10.837388,10.835741,10.839804};
    double[] lonres4={76.691545,76.697359,76.690875,76.685844};
    double[] latres5={10.857591,10.849796,10.831788,10.856053};
    double[] lonres5={76.667957,76.670863,76.692775,76.669410};
    Firebase firebase;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        obj.onMapReady(obj.mMap);
//        firebase=new Firebase("https://sojournhappy-c5e87.firebaseio.com/");
//        Addpolygon();
        startActivity(new Intent(notificationservice.this,allimpplaces.class));
    }
    private void sendNotification(String raj, String content) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(notificationservice.this)
                .setSmallIcon(R.mipmap.applogo)
                .setContentTitle(raj)
                .setContentText(content);
        NotificationManager manager= (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        builder.setSound(Settings.System.DEFAULT_RINGTONE_URI);
//        Uri not= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
//        Ringtone r=RingtoneManager.getRingtone(this,not);
//        r.play();
        Intent intent=new Intent(this,MapsActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(contentIntent);
        builder.setVisibility(VISIBILITY_PUBLIC);
        Notification notification=builder.build();
//        builder.setVibrate(new long[] {1000,1000,1000,1000,1000});
//        notification.category|=Notification.CATEGORY_SERVICE;
//        notification.flags|=Notification.FLAG_SHOW_LIGHTS;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        final MediaPlayer media=MediaPlayer.create(this,R.raw.emergencyring);
        media.start();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("WARNING !!!");
        alert.setMessage("You entered the protected Area");
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                media.stop();
            }
        });
        alert.show();

//        notification.defaults |= Notification.DEFAULT_VIBRATE;
        manager.notify(new Random().nextInt(),notification);
    }
    private void Addpolygon() {
        PolygonOptions polygon=new PolygonOptions().add(new LatLng(10.880595,76.665895),
                new LatLng(10.905227,76.672396),new LatLng(10.889213,76.720805),new LatLng(10.863000,76.719689))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        obj.mMap.addPolygon(polygon);
        PolygonOptions polygon1=new PolygonOptions().add(new LatLng(10.855666,76.651025),
                new LatLng(10.837711,76.670594),new LatLng(10.834844,76.657119))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        obj.mMap.addPolygon(polygon1);
        PolygonOptions polygon2=new PolygonOptions().add(new LatLng(10.958774,76.673546),
                new LatLng(10.941415,76.667452),new LatLng(10.950011,76.692772))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        obj.mMap.addPolygon(polygon2);
        PolygonOptions polygon3=new PolygonOptions().add(new LatLng(10.925825,76.682215),
                new LatLng(10.933578,76.707363),new LatLng(10.920094,76.701184))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        obj.mMap.addPolygon(polygon3);
        PolygonOptions polygon4=new PolygonOptions().add(new LatLng(10.842329,76.691545),
                new LatLng(10.837388,76.697359),new LatLng(10.835741,76.690875),new LatLng(10.839804,76.685844))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        obj.mMap.addPolygon(polygon4);
        PolygonOptions polygon5=new PolygonOptions().add(new LatLng(10.857591,76.667957),
                new LatLng(10.849796,76.670863),new LatLng(10.831788,76.692775),new LatLng(10.856053,76.669410))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        obj.mMap.addPolygon(polygon5);
        list1=new ArrayList<>();
        list2=new ArrayList<>();
        list3=new ArrayList<>();
        list4=new ArrayList<>();
        list5=new ArrayList<>();
        list6=new ArrayList<>();
        for(int i=0;i<latituderestrictedplacemalampuzha.length;i++){
            list1.add(new LatLng(latituderestrictedplacemalampuzha[i],longituderestrictedplacemalampuzha[i]));
        }
        for(int i=0;i<latres1.length;i++){
            list2.add(new LatLng(latres1[i],lonres1[i]));
        }
        for(int i=0;i<latres2.length;i++){
            list3.add(new LatLng(latres2[i],lonres2[i]));
        }
        for(int i=0;i<latres3.length;i++){
            list4.add(new LatLng(latres3[i],lonres3[i]));
        }
        for(int i=0;i<latres4.length;i++){
            list5.add(new LatLng(latres4[i],lonres4[i]));
        }
        for(int i=0;i<latres5.length;i++){
            list6.add(new LatLng(latres5[i],lonres5[i]));
        }
        if(src!=null) {
            if (PolyUtil.containsLocation(src, list1, true) || PolyUtil.containsLocation(src, list2, true) || PolyUtil.containsLocation(src, list3, true) || PolyUtil.containsLocation(src, list4, true) ||
                    PolyUtil.containsLocation(src, list5, true) || PolyUtil.containsLocation(src, list6, true)) {
//            Intent intent=new Intent(getApplicationContext(),broadcast.class);
//            PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_IMMUTABLE);
                sendNotification("Sojourn", "You have entered into the dangerous area!!!");
                if (id == null)
                    id = firebase.child("my location").push().getKey();
                firebase.child("my location").child(id).child("lat").setValue(src.latitude);
                firebase.child("my location").child(id).child("long").setValue(src.longitude);

//            reference.child(user.getUid()).child("latitude").setValue(latitude);
//            reference.child(user.getUid()).child("longitude").setValue(longitude);
//            reference.child(user.getUid()).child("issharing").setValue(true);
            }
        }
//        mMap.addPolygon(new PolygonOptions().add(new LatLng()))
    }

}
