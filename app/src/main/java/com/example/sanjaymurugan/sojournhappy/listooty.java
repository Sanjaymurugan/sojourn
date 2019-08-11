package com.example.sanjaymurugan.sojournhappy;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class listooty extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView list;
//    Double[] latitude1={10.830809,10.830062,10.825015,10.824003,10.834132,10.840328,10.867934,10.845133,10.831392,10.823528};
//    Double[] longitude2={76.683648, 76.680332, 76.688768, 76.669589, 76.682141, 76.676991, 76.657679, 76.725872, 76.724885, 76.669690};
    int[] placesmalampuzha={R.string.malampuzhadam,R.string.snakerehabcentre,R.string.Fishpark
            ,R.string.RockGarden,R.string.rope,R.string.fantasyp,R.string.kavareserve,R.string.kavaeastview,
            R.string.birdwatcharea,R.string.damviewpointmalampuzha};
    int[] nameofplacesmalampuzha={R.string.malampuzha_dam, R.string.snake_rehabilitation_centre, R.string.fishpark, R.string.rockgarden
            , R.string.ropeway, R.string.fantasypark, R.string.kavareservoirviewpoint, R.string.kavaviewpoint, R.string.malampuzhabirdwatchingarea,
            R.string.malampuzhadamviewpoint};
    String images[]={"https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/dam_malampuzha.jpg?alt=media&token=c3340164-e3f5-4d45-81c0-bdcf0e9d2547",
            "https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/snake_center.jpg?alt=media&token=451136b2-0862-4e35-b85c-02392b3e3227",
            "https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/aquarium.jpg?alt=media&token=ade2f2a1-da41-4346-a62a-1878d1eca001",
            "https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/rock_garden.jpg?alt=media&token=23ea58e1-2473-4c1b-8789-df7fc6a5552d",
            "https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/rope.jpg?alt=media&token=0b81b423-a224-4cf1-ada7-8e85d2196f11",
            "https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/fantasy_park.jpg?alt=media&token=abea9057-bbd9-4a77-be75-eff489c37627",
            "https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/kava.jpg?alt=media&token=65631a49-74af-4181-a1b0-d6a826fe46fb",
            "https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/kava_east.jpg?alt=media&token=ab1105f8-c312-4f3c-8977-0b63c1875519",
            "https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/bird_watch.jpg?alt=media&token=ee33e5b0-3a04-45d6-83f6-45f3f2c12a58",
            "https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/dam.jpg?alt=media&token=0ce13b98-879e-459a-99e0-534bde19f45a"};
    //    int images[] = {R.mipmap.naturepic1, R.mipmap.naturepic2, R.mipmap.naturepic3, R.mipmap.naturepic4, R.mipmap.naturepic5, R.mipmap.naturepic6, R.mipmap.naturepic7, R.mipmap.naturepic8, R.mipmap.naturepic9, R.mipmap.naturepic10};
    Double latitudemalampuzha[]={10.831009,10.829936,10.832701,10.824477,10.833621,10.823560,10.830696,10.845235,10.884170,10.831662};
    Double longitudemalampuzha[]={76.683744,76.680343,76.680890,76.688813,76.682221,76.669691,76.724885,76.725980,76.663779,76.683632};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listooty);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitle("Malampuzha");
//        placenametv=(TextView)findViewById(R.id.placetemperature);
//        placecurrtemp=(TextView)findViewById(R.id.placetemperature);

        list = (ListView) findViewById(R.id.list_item);
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        String provider = locationManager.getBestProvider(new Criteria(), false);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        assert locationManager!=null;
//        Location location = locationManager.getLastKnownLocation(provider);
//        if(location!=null) {
//            Double latitude = location.getLatitude();
//            Double langitude = location.getLongitude();
//            DownloadTask downloadTask = new DownloadTask();
//            downloadTask.execute("http://api.openweathermap.org/data/2.5/weather?lat=" + String.valueOf(latitude) + "&lon=" + String.valueOf(langitude) + "&appid=62fad5b4524e746666dfea5124e8e863");
//            Toast.makeText(listooty.this, latitude + " " + langitude, Toast.LENGTH_SHORT).show();
//        }
            baseadapter baseadapter = new baseadapter(getApplicationContext(), images,nameofplacesmalampuzha);
            list.setAdapter(baseadapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(listooty.this, allimpplaces.class);
                    intent.putExtra("bundle", images[position]);
                    intent.putExtra("textview",placesmalampuzha[position]);
                    intent.putExtra("details",nameofplacesmalampuzha[position]);
                    intent.putExtra("latitudesmalampuzha",latitudemalampuzha[position]);
                    intent.putExtra("longitudesmalampuzha",longitudemalampuzha[position]);
                    startActivity(intent);
                }
            });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimaryDark));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        startActivity(new Intent(listooty.this,Home.class));
        this.finish();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.listooty, menu);
////        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
////        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(listooty.this,Home.class));
        } else if (id == R.id.nav_admin) {
            startActivity(new Intent(listooty.this,appadminlogintonext.class));
        } else if (id == R.id.nav_emergency) {
            startActivity(new Intent(listooty.this,Emergency.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(listooty.this,Help.class));
        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(listooty.this,About_us.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        this.finish();
        return true;
    }
}
