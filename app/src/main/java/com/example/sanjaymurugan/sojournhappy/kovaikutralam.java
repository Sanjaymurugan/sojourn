package com.example.sanjaymurugan.sojournhappy;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class kovaikutralam extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
        private ImageView placevaalpaarai;
    TextView vaalpaarai,vaalpaaraitemp,vaaldesc,seasonkutralamkovai;
    Double latitudevaalparai=10.939198;
    Double longitudevaalparai=76.689281;
    Double temperaturekkutralam=273.15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kovaikutralam);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitle("Coimbatore");
        placevaalpaarai=(ImageView)findViewById(R.id.coimbatorevaalparai);
        seasonkutralamkovai=(TextView)findViewById(R.id.seasonkutralam);
        vaalpaarai=(TextView)findViewById(R.id.vaalpaarainame);
        Glide.with(kovaikutralam.this).load("https://firebasestorage.googleapis.com/v0/b/sojournhappy-c5e87.appspot.com/o/kk.jpg?alt=media&token=8863142c-d984-403a-bbd6-4a5f6cce9963").into(placevaalpaarai);
        vaalpaaraitemp=(TextView)findViewById(R.id.vaaltemp);
        vaaldesc=(TextView)findViewById(R.id.vaaldesc);
        placevaalpaarai.setOnClickListener(this);
        vaalpaarai.setOnClickListener(this);
        Calendar calendar=Calendar.getInstance();
        int date=calendar.get(Calendar.DATE);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        if(month==4||month==5||month==8||month==9){
            seasonkutralamkovai.setText(" SEASON ");
        }
        findweathervaalparai(latitudevaalparai,longitudevaalparai);



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

    private void findweathervaalparai(Double latitudevaalparai, Double longitudevaalparai) {
        String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + latitudevaalparai + "&lon=" + longitudevaalparai + "&appid=eca87c928d929935f78decd69e7fc312 ";
//           String url="http://api.openweathermap.org/data/2.5/weather?lat=10.939198&lon=76.689281&appid=eca87c928d929935f78decd69e7fc312 ";


//
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject  = response.getJSONObject("main");
                    JSONArray jsonArray=response.getJSONArray("weather");
                    JSONObject object=jsonArray.getJSONObject(0);
                    String vtemp=String.valueOf(jsonObject.getDouble("temp"));
                    Double temp2=Double.parseDouble(vtemp)-temperaturekkutralam;
                    int temperatureanswerkk=Integer.valueOf(temp2.intValue());
                    String vdesc=object.getString("description");
                    String  vcity=response.getString("name");
                    vaalpaarai.setText("kovai kutralam");
                    vaaldesc.setText(vdesc);
                    vaalpaaraitemp.setText(temperatureanswerkk+"°C");

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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        startActivity(new Intent(kovaikutralam.this,Home.class));
        this.finish();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.kovaikutralam, menu);
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
            startActivity(new Intent(kovaikutralam.this,Home.class));
        } else if (id == R.id.nav_admin) {
            startActivity(new Intent(kovaikutralam.this,appadminlogintonext.class));
        } else if (id == R.id.nav_emergency) {
            startActivity(new Intent(kovaikutralam.this,Emergency.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(kovaikutralam.this,Help.class));
        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(kovaikutralam.this,About_us.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        this.finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.coimbatorevaalparai:
            case R.id.vaalpaarainame:
                Intent vaalpaaraiintent=new Intent(kovaikutralam.this,listvaalpaarai.class);
                startActivity(vaalpaaraiintent);
                break;
        }
    }
}
