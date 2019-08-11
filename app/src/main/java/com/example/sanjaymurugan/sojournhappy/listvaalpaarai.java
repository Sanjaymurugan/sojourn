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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class listvaalpaarai extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView vaalpaarailv;
    int[] placekovaikutralam={R.string.kovaikutralamdesc,R.string.eshadesc};
    int nameofcityvaalparai[]={R.string.kovaikutralam,R.string.eshayoga};
    String images[] = {"https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/KovaiK.jpg?alt=media&token=f3e9f95d-7386-4cfc-9c43-49438532d1f9",
            "https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/isha.jpg?alt=media&token=514316f8-cffa-4337-b9b6-148c56f6e44a"};
    Double latitudekovaikutralam[]={10.939224,10.978303};
    Double longitudekovaikutralam[]={76.701731,76.735307};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listvaalpaarai);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitle("Kovaikutralam");
//        placenametv=(TextView)findViewById(R.id.placetemperature);
//        placecurrtemp=(TextView)findViewById(R.id.placetemperature);
        vaalpaarailv=(ListView)findViewById(R.id.listvaal);

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
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
//            DownloadTask downloadTask = new DownloadTask();
//            downloadTask.execute("http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=62fad5b4524e746666dfea5124e8e863");
//        }
            baseadapter baseadapter = new baseadapter(getApplicationContext(), images,nameofcityvaalparai);
            vaalpaarailv.setAdapter(baseadapter);
            vaalpaarailv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(listvaalpaarai.this, allimpplaces.class);
                    intent.putExtra("bundle", images[position]);
                    intent.putExtra("textview",placekovaikutralam[position]);
                    intent.putExtra("details",nameofcityvaalparai[position]);
                    intent.putExtra("latitudesmalampuzha",latitudekovaikutralam[position]);
                    intent.putExtra("longitudesmalampuzha",longitudekovaikutralam[position]);
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
        startActivity(new Intent(listvaalpaarai.this,Home.class));
        this.finish();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.listvaalpaarai, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(listvaalpaarai.this,Home.class));
        } else if (id == R.id.nav_admin) {
            startActivity(new Intent(listvaalpaarai.this,appadminlogintonext.class));
        } else if (id == R.id.nav_emergency) {
            startActivity(new Intent(listvaalpaarai.this,Emergency.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(listvaalpaarai.this,Help.class));
        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(listvaalpaarai.this,About_us.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        this.finish();
        return true;
    }

}
