package com.example.sanjaymurugan.sojournhappy;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;

public class appadminvisit extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Firebase fire;
    FirebaseAuth firebaseAuth;
public static ArrayList<String> key=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Firebase.setAndroidContext(this);
        firebaseAuth=FirebaseAuth.getInstance();
        fire=new Firebase("https://sojournhappy-c5e87.firebaseio.com/my location");
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert1=new AlertDialog.Builder(appadminvisit.this);
                alert1.setTitle("Are you sure?");
                alert1.setMessage("Do you want to logout?");
                alert1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        startActivity(new Intent(appadminvisit.this,appadminlogintonext.class));
                    }
                });
                alert1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert1.show();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        fire.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    key.add(dataSnapshot1.getKey());
                    String latitude=dataSnapshot1.child("lat").getValue().toString();
                    String longitude=dataSnapshot1.child("long").getValue().toString();
                    MarkerOptions markerOptions = new MarkerOptions();
                    Toast.makeText(appadminvisit.this,latitude+","+longitude,Toast.LENGTH_SHORT).show();
                    LatLng latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                    markerOptions.position(latLng);
                    markerOptions.title("anonymous person");
                    mMap.addMarker(markerOptions);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f));
                    sendNotification("Sojourn", "Someone has entered into the dangerous area!!!");
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Toast.makeText(appadminvisit.this,"hello",Toast.LENGTH_SHORT).show();
        }

    private void sendNotification(String raj, String content) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(appadminvisit.this)
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

        // Add a marker in Sydney and move the camera

    }