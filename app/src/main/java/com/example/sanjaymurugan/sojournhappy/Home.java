package com.example.sanjaymurugan.sojournhappy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
//    private ViewPager viewPager;
    private ImageView coimbatorehomeimg;
    private ImageView palakadhomeimg;
    private TextView palakad;
    private TextView Coimbatore;
    private TextView palakad_des;
    private TextView Coimbatore_des;
    custompageadapter custompageadapter1;
    public static FirebaseStorage storage;
    private StorageReference storageref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
//        getWindow().setNavigationBarColor(@ColorInt int color);
//        viewPager=(ViewPager)findViewById(R.id.viewpager);
        coimbatorehomeimg=(ImageView)findViewById(R.id.coimbatoreimgviewhome);
        palakadhomeimg=(ImageView)findViewById(R.id.palakadviewhome);
        palakad=(TextView)findViewById(R.id.cbe);
        palakad_des=(TextView)findViewById(R.id.palakad_des);
        Coimbatore=(TextView)findViewById(R.id.palakad);
        Coimbatore_des=(TextView)findViewById(R.id.coimbatore_des);
        storage=FirebaseStorage.getInstance();
        Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/Palakad.JPG?alt=media&token=6d049c0e-f938-4186-aa13-a927d413911a").into(coimbatorehomeimg);
       Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/Coimbatore.jpg?alt=media&token=effafc05-f081-4c87-b8ac-b364e8e7c5b5").into(palakadhomeimg);
        custompageadapter1=new custompageadapter(this);
//        viewPager.setAdapter(custompageadapter1);
//        Timer timer=new Timer();
//        timer.scheduleAtFixedRate(new timerTask(),6000,6000);
        coimbatorehomeimg.setOnClickListener(this);
        palakadhomeimg.setOnClickListener(this);
        palakad.setOnClickListener(this);
        palakad_des.setOnClickListener(this);
        Coimbatore.setOnClickListener(this);
        Coimbatore_des.setOnClickListener(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.setBackgroundColor(Color.BLUE);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimaryDark));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
//    public class timerTask extends TimerTask{
//
//        @Override
//        public void run() {
//            Home.this.runOnUiThread( new Runnable() {
//                @Override
//                public void run() {
//                    if(viewPager.getCurrentItem()==0){
//                        viewPager.setCurrentItem(1);
//                    }else if (viewPager.getCurrentItem()==1){
//                        viewPager.setCurrentItem(2);
//                    }else if (viewPager.getCurrentItem()==2) {
//                        viewPager.setCurrentItem(0);
//                    } else {
//                        viewPager.setCurrentItem(0);
//                    }
//                }
//            });
//        }
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(Home.this,Home.class));
        } else if (id == R.id.nav_admin) {
            Intent apploginintent=new Intent(this,appadminlogintonext.class);
            startActivity(apploginintent);
        } else if (id == R.id.nav_emergency) {
            startActivity(new Intent(this,Emergency.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(Home.this,Help.class));
        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(Home.this,About_us.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        this.finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.coimbatoreimgviewhome:
            case R.id.cbe:
            case R.id.palakad_des:
                Intent coimbatoreintent=new Intent(Home.this,coimbatore.class);
                startActivity(coimbatoreintent);
                break;
            case R.id.palakadviewhome:
            case R.id.palakad:
            case R.id.coimbatore_des:
                Intent palakadintent=new Intent(Home.this,kovaikutralam.class);
                startActivity(palakadintent);
                break;
        }
    }
}
