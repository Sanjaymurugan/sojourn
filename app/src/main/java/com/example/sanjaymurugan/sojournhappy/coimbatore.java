package com.example.sanjaymurugan.sojournhappy;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class coimbatore extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private ImageView placeooty;
    TextView malampuzha,malatemp,maladesc,seasonmala;
    Double latitudemalampuzha=10.8307;
    Double longitudemalampuzha=76.6837;
    double constant=273.15;
    //lat long for malampuzha
//    Double lat2[]={10.8307,10.3236};
//    Double long2[]={76.6837,76.9510};
    //lat long for vaalpaarai
//    public  static int temp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coimbatore2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitle("Palakkad");
        placeooty=(ImageView)findViewById(R.id.coimbatoreooty);
        seasonmala=(TextView)findViewById(R.id.seasonmalampuzha);
       Glide.with(coimbatore.this).load("https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/dam_malampuzha.jpg?alt=media&token=c3340164-e3f5-4d45-81c0-bdcf0e9d2547").into(placeooty);
        malampuzha=(TextView)findViewById(R.id.placename);
        malatemp=(TextView)findViewById(R.id.placetemperature);
        maladesc=(TextView)findViewById(R.id.seasonaldescription);
        malampuzha.setOnClickListener(this);
        placeooty.setOnClickListener(this);
        Calendar calendar=Calendar.getInstance();
        int date=calendar.get(Calendar.DATE);
        int month=calendar.get(Calendar.MONTH)+1;
        int year=calendar.get(Calendar.YEAR);
        if(month==4||month==5||month==10||month==11){
            seasonmala.setText(" SEASON ");
        }
        findweather(latitudemalampuzha,longitudemalampuzha);

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
        startActivity(new Intent(coimbatore.this,Home.class));
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.coimbatore, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(coimbatore.this,Home.class));
        } else if (id == R.id.nav_admin) {
            startActivity(new Intent(coimbatore.this,appadminlogintonext.class));
        } else if (id == R.id.nav_emergency) {
            startActivity(new Intent(coimbatore.this,Emergency.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(coimbatore.this,Help.class));
        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(coimbatore.this,About_us.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        this.finish();
        return true;
    }
    private void findweather(double latitudemalampuzha,double longitudemalampuzha) {
//        for(int i=0;i<10;i++) {
//           //lat lon for malampuzha dam
//        int i;
        String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + latitudemalampuzha + "&lon=" + longitudemalampuzha + "&appid=eca87c928d929935f78decd69e7fc312 ";
//            url="http://api.openweathermap.org/data/2.5/weather?lat="+lat1+"&lon="+lon1+"&appid=eca87c928d929935f78decd69e7fc312 ";


//
// for snake rehabilitation centre
////            url="http://api.openweathermap.org/data/2.5/weather?lat=10.830189&lon=76.680375&appid=eca87c928d929935f78decd69e7fc312 ";
//////          //for aquarium
////            url="http://api.openweathermap.org/data/2.5/weather?lat=10.827204&lon=76.668382&appid=eca87c928d929935f78decd69e7fc312 ";
//////            //for rock garden
////          url="http://api.openweathermap.org/data/2.5/weather?lat=10.824542&lon=76.688753&appid=eca87c928d929935f78decd69e7fc312 ";
//////          //for rope way
////          url="http://api.openweathermap.org/data/2.5/weather?lat=10.833642&lon=76.682103&appid=eca87c928d929935f78decd69e7fc312 ";
//////          //for fantasy park
////          url="http://api.openweathermap.org/data/2.5/weather?lat=10.823655&lon=76.669682&appid=eca87c928d929935f78decd69e7fc312 ";
//////          //for dam view point
////          url="http://api.openweathermap.org/data/2.5/weather?lat=10.832085&lon=76.683501&appid=eca87c928d929935f78decd69e7fc312 ";
//////         //for bird watching area
////          url="http://api.openweathermap.org/data/2.5/weather?lat=10.879301&lon=76.658684&appid=eca87c928d929935f78decd69e7fc312 ";
//////        // for kava reservoir view point
////           url="http://api.openweathermap.org/data/2.5/weather?lat=10.830708&lon=76.724992&appid=eca87c928d929935f78decd69e7fc312 ";
//////             //for kava view point east
////          url="http://api.openweathermap.org/data/2.5/weather?lat=10.844631&lon=76.725920&appid=eca87c928d929935f78decd69e7fc312 ";
//        }
//        final int finalI = i;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject  = response.getJSONObject("main");
                    JSONArray jsonArray=response.getJSONArray("weather");
                    JSONObject object=jsonArray.getJSONObject(0);
                    String temperature=String.valueOf(jsonObject.getDouble("temp"));
                    Double temp=Double.parseDouble(temperature)-constant;
                    int tempanswermalampuzha=Integer.valueOf(temp.intValue());
                    String description=object.getString("description");
                    String  cityname=response.getString("name");
//                    if(temp==0) {
                        malampuzha.setText("malampuzha");
                        maladesc.setText(description);
                        malatemp.setText(tempanswermalampuzha+"°C");
//                        Toast.makeText(coimbatore.this,cityname+" "+temp+" ",Toast.LENGTH_LONG).show();
//                        temp=temp+1;

//                    }
//                    else {
//                        vaalpaarai.setText(cityname);
//                        vaaldesc.setText(description);
//                        vaalpaaraitemp.setText(temperature);
//                        Toast.makeText(coimbatore.this,cityname+" "+temp,Toast.LENGTH_LONG).show();
//                        temp=0;
//                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.placename:
            case R.id.coimbatoreooty:
                Intent ootyintent=new Intent(coimbatore.this,listooty.class);
                startActivity(ootyintent);
                break;
        }
    }
}
