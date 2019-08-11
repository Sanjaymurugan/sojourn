package com.example.sanjaymurugan.sojournhappy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Splashscreen extends AppCompatActivity {
ImageView logosplash;
TextView  appnamesplash;
private static int SPLASHTIMEOUT=5000;
public NetworkInfo wi,mi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        logosplash=(ImageView)findViewById(R.id.splashimglogo);
        appnamesplash=(TextView)findViewById(R.id.splashtextappname);
//        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/sojournhappy.appspot.com/o/SojournFinal2.png?alt=media&token=00cd2753-84b1-48d9-856f-87e5715db9a8").into(logosplash);
        ConnectivityManager cm=(ConnectivityManager)this.getSystemService(this.CONNECTIVITY_SERVICE);
        wi=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        mi=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Network");
        alert.setMessage("Network not enabled. Do you want to go to the settings menu?");
        alert.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Splashscreen.this.finish();
            }
        });
        if(network()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Splashscreen.this, Home.class));
                    finish();
                }
            }, SPLASHTIMEOUT);
        }else
            alert.show();
    }
    boolean network(){
        if ((wi != null && wi.isConnected()) || (mi != null && mi.isConnected())) {
            return true;
        } else {
            return false;
        }
    }
}
