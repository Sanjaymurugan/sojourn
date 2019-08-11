package com.example.sanjaymurugan.sojournhappy;

import android.*;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
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
import com.firebase.client.Firebase;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v4.app.NotificationCompat.CATEGORY_SERVICE;
import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;

public class allimpplaces extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback{
    private ImageView setclickedimgooty;
    private TextView setdescriptionooty;
    public static GoogleMap mMap;
    private TextView setdetailooty;
    public static TextView distancemap,duration;
    Firebase firebase;
    IGoogleclient mservices;
    FirebaseUser user;
    private LocationManager locationManager;
    DatabaseReference reference;
    GeoFire geoFire;
    ProgressDialog progressDialog;
    Marker mycurr;
    List<LatLng> list1,list2,list3,list4,list5,list6,list7;
    public static String id;
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
    private static final long LOCAL_REFRESHTIME=100;
    private static final long LOCAL_REFRESHDISTANCE=1000;
//    LatLng latlngmap=new LatLng(11.405880,76.697151);
   public static Double latitudemalakutralam,longitudemalakutralam;
   public static Double latsrc,latdest;
   public static LatLng src,dest,source;
   public static Double latitude,longitude;
    public NetworkInfo wi,mi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allimpplaces);

        final Dialog dialog=new Dialog(allimpplaces.this);
        dialog.setContentView(R.layout.quotes);
        dialog.setTitle("Message");
        dialog.show();
        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 5*1000);

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
                allimpplaces.this.finish();
            }
        });
        if(network()){

        }else
            alert.show();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Firebase.setAndroidContext(this);
        firebase=new Firebase("https://sojournhappy-c5e87.firebaseio.com/");
        reference= FirebaseDatabase.getInstance().getReference("my_location");
        geoFire=new GeoFire(reference);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

//        setuplocation();
        displayLocation();
        mservices=common.getgoogleclient();
        final BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.btmnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//            }
//        });
        distancemap=(TextView)findViewById(R.id.distancedurationmap);
        duration=(TextView)findViewById(R.id.durationmap);
        Bundle extras = getIntent().getExtras();
            String img = extras.getString("bundle");
            int img1 = extras.getInt("textview");
            int img2 = extras.getInt("details");
            latitudemalakutralam = extras.getDouble("latitudesmalampuzha");
            longitudemalakutralam = extras.getDouble("longitudesmalampuzha");
            dest = new LatLng(latitudemalakutralam, longitudemalakutralam);
            ImageView setclickedimgooty = (ImageView) findViewById(R.id.showplacesooty);
            Glide.with(allimpplaces.this).load(img).into(setclickedimgooty);
            setdescriptionooty = (TextView) findViewById(R.id.descriptionplacesooty);
            setdescriptionooty.setText(img1);
            setdetailooty = (TextView) findViewById(R.id.detailooty);
            setdetailooty.setText(img2);
        String provider=Settings.Secure.getString(getContentResolver(),Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.contains("gps")){
            AlertDialog.Builder alert1=new AlertDialog.Builder(this);
            alert1.setTitle("GPS");
            alert1.setMessage("GPS not enabled. Do you want to go to the settings menu?");
            alert1.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            alert1.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alert1.show();
        }
    }

    private void NearbyPlace(final String placetype) {
        mMap.clear();
        String Url=getUrl(latsrc, latdest,placetype);
        mservices.getNearbypaces(Url)
                .enqueue(new Callback<pojo>() {
                    @Override
                    public void onResponse(Call<pojo> call, Response<pojo> response) {
                        if(response.isSuccessful()){
                            for(int i=0;i<response.body().getResults().length;i++){
                                MarkerOptions markerOptions=new MarkerOptions();
                                Results googleplace=response.body().getResults()[i];
                                double lat=Double.parseDouble(googleplace.getGeometry().getLocation().getLat());
                                double lon=Double.parseDouble(googleplace.getGeometry().getLocation().getLng());
//                                Toast.makeText(allimpplaces.this, "latitude"+lat+",Longitude"+lon, Toast.LENGTH_SHORT).show();
                                String placename=googleplace.getName();
                                String vicinity=googleplace.getVicinity();
                                LatLng latLng=new LatLng(lat,lon);
                                markerOptions.position(latLng);
                                markerOptions.title(placename);
                                if(placetype.equals("hospital"))
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                                else if(placetype.equals("restaurant"))
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                                else if(placetype.equals("atm"))
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                                else if(placetype.equals("gas_station"))
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//                                else if(placetype.equals("supermarket"))
//                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                else
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                                mMap.addMarker(markerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12.0f));
                            }
                        }                    }

                    @Override
                    public void onFailure(Call<pojo> call, Throwable t) {

                    }
                });
    }

    private String getUrl(Double latitudenearplace, Double longitudenearplace, String placetype) {
        StringBuilder googleurl=new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleurl.append("location="+latitudenearplace+","+longitudenearplace);
        googleurl.append("&radius="+3000);
        googleurl.append("&types="+placetype);
        googleurl.append("&sensor=true");
        googleurl.append("&key="+getResources().getString(R.string.browser_key));
        Log.d("geturl",googleurl.toString());
        return googleurl.toString();

    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.allimpplaces, menu);
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

//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_home) {
//            // Handle the camera action
//        } else if (id == R.id.nav_map) {
//
//        } else if (id == R.id.nav_admin) {
//
//        } else if (id == R.id.nav_emergency) {
//
//        } else if (id == R.id.nav_help) {
//
//        } else if (id == R.id.nav_aboutus) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //declare googlemap
        mMap = googleMap;


        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        assert locationManager != null;
        //access and declare location
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCAL_REFRESHTIME, LOCAL_REFRESHDISTANCE, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //get latitude and longitude
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    latsrc=latitude;
                    latdest=longitude;
                    LatLng latLngsrc=new LatLng(latitude,longitude);
                    //save latitude and longitude in variable latlng
                    LatLng latlngdest = new LatLng(latitudemalakutralam, longitudemalakutralam );
                    String url=getDirectionUrl(latLngsrc,latlngdest);
                    src=new LatLng(latitude,longitude);
                    Addpolygon();
                    //declare and call downloadtask class
                    DownloadTask downloadTask=new DownloadTask();
                    downloadTask.execute(url);
                    //move camera to my position
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngsrc,10f));
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });}
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCAL_REFRESHTIME,
                    LOCAL_REFRESHDISTANCE, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            //get latitude and longitude
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            //call src latlng
                            LatLng latLng_src = new LatLng(latitude, longitude);
                            //call dest latlng
                            LatLng latLng_dest=new LatLng(latitudemalakutralam, longitudemalakutralam);
                            String url=getDirectionUrl(latLng_src,latLng_dest);
                            //call downloadtask class
                            DownloadTask downloadTask=new DownloadTask();
                            downloadTask.execute(url);
                            //move camera to my location
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng_src, 10.1f));
                        }


                        @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
        LatLng dangerlatlng=new LatLng(10.849576, 76.680572);

//        mMap.addCircle(new CircleOptions().center(dangerlatlng)
//        .radius(500)
//        .strokeColor(Color.BLUE)
//        .fillColor(Color.CYAN)
//        .strokeWidth(5.0f));

        GeoQuery geoQuery=geoFire.queryAtLocation(new GeoLocation(dangerlatlng.latitude,dangerlatlng.longitude),0.5f);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                Addpolygon();
//                sendNotification("raj",String.format(" %s entered the dangerous area",key));

            }

            @Override
            public void onKeyExited(String key) {
                Addpolygon();
               firebase.child("my location").child(id).removeValue();

//                sendNotification("raj",String.format(" %s exited the dangerous area",key));

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                Addpolygon();
//                sendNotification("move",String.format(" %s moved the dangerous area",key,location.latitude,location.longitude));

            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                Log.e("error"," "+error);

            }
        });
        LatLng dangerlatlng2=new LatLng(10.939308, 76.691399);

//        mMap.addCircle(new CircleOptions().center(dangerlatlng)
//        .radius(500)
//        .strokeColor(Color.BLUE)
//        .fillColor(Color.CYAN)
//        .strokeWidth(5.0f));

        GeoQuery geoQuery2=geoFire.queryAtLocation(new GeoLocation(dangerlatlng2.latitude,dangerlatlng2.longitude),10);
        geoQuery2.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                Addpolygon();
//                sendNotification("raj",String.format(" %s entered the dangerous area",key));

            }

            @Override
            public void onKeyExited(String key) {
                Addpolygon();
                firebase.child("my location").child(id).removeValue();

//                sendNotification("raj",String.format(" %s exited the dangerous area",key));

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                Addpolygon();
//                sendNotification("move",String.format(" %s moved the dangerous area",key,location.latitude,location.longitude));

            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                Log.e("error"," "+error);

            }
        });

        LatLng dangerlatlng3=new LatLng(10.855906, 77.034760);

//        mMap.addCircle(new CircleOptions().center(dangerlatlng)
//        .radius(500)
//        .strokeColor(Color.BLUE)
//        .fillColor(Color.CYAN)
//        .strokeWidth(5.0f));

        GeoQuery geoQuery3=geoFire.queryAtLocation(new GeoLocation(dangerlatlng3.latitude,dangerlatlng3.longitude),.5f);
        geoQuery3.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                Addpolygon();
//                sendNotification("raj",String.format(" %s entered the dangerous area",key));

            }

            @Override
            public void onKeyExited(String key) {
                Addpolygon();
                firebase.child("my location").child(id).removeValue();

//                sendNotification("raj",String.format(" %s exited the dangerous area",key));

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                Addpolygon();
//                sendNotification("move",String.format(" %s moved the dangerous area",key,location.latitude,location.longitude));

            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                Log.e("error"," "+error);

            }
        });

        if(location!=null) {
            //get latitude and longitude

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            //display my current location
            mMap.setMyLocationEnabled(true);
        }
        progressDialog.dismiss();
}

    private void Addpolygon() {
        PolygonOptions polygon=new PolygonOptions().add(new LatLng(10.880595,76.665895),
                new LatLng(10.905227,76.672396),new LatLng(10.889213,76.720805),new LatLng(10.863000,76.719689))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        mMap.addPolygon(polygon);
        PolygonOptions polygon1=new PolygonOptions().add(new LatLng(10.855666,76.651025),
                new LatLng(10.837711,76.670594),new LatLng(10.834844,76.657119))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        mMap.addPolygon(polygon1);
        PolygonOptions polygon2=new PolygonOptions().add(new LatLng(10.958774,76.673546),
                new LatLng(10.941415,76.667452),new LatLng(10.950011,76.692772))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        mMap.addPolygon(polygon2);
        PolygonOptions polygon3=new PolygonOptions().add(new LatLng(10.925825,76.682215),
                new LatLng(10.933578,76.707363),new LatLng(10.920094,76.701184))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        mMap.addPolygon(polygon3);
        PolygonOptions polygon4=new PolygonOptions().add(new LatLng(10.842329,76.691545),
                new LatLng(10.837388,76.697359),new LatLng(10.835741,76.690875),new LatLng(10.839804,76.685844))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        mMap.addPolygon(polygon4);
        PolygonOptions polygon5=new PolygonOptions().add(new LatLng(10.857591,76.667957),
                new LatLng(10.849796,76.670863),new LatLng(10.831788,76.692775),new LatLng(10.856053,76.669410))
                .fillColor(0x7Fd70005)
                .strokeColor(0x7F000000)
                .strokeWidth(15f);
        mMap.addPolygon(polygon5);
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

    public String getDirectionUrl(LatLng source,LatLng destination){
        String str_origin="origin="+source.latitude+","+source.longitude;
        String str_destination="destination="+destination.latitude+","+destination.longitude;
        String sensor="sensor=false";
        String parameters=str_origin+"&"+str_destination+"&"+sensor;
        String output="json";
        String url="https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
        return url;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        startService(new Intent(allimpplaces.this,notificationservice.class));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_destination:
                mMap.clear();
                String url=getDirectionUrl(src,dest);
                progressDialog.dismiss();
                DownloadTask downloadTask=new DownloadTask();
                downloadTask.execute(url);
//                mMap.addPolygon(new PolygonOptions().add(new LatLng(10.853762,77.033276),
//                        new LatLng(10.853815,77.036317),new LatLng(10.851856,77.034743))
//                        .fillColor(Color.BLUE)
//                        .strokeColor(Color.CYAN)
//                        .strokeWidth(15f));
                Addpolygon();
                break;
            case R.id.nav_hospital:
                NearbyPlace("hospital");
                break;
            case R.id.nav_restaurant:
                NearbyPlace("restaurant");
                break;
            case R.id.nav_Atm:
                NearbyPlace("atm");
                break;
            case R.id.nav_parking:
                NearbyPlace("gas_station");
                break;
            default:break;
        }
                        return true;

    }
    private void displayLocation(){
        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        assert locationManager != null;
        //access and declare location
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //get latitude and longitude
        if (location!=null) {
            final double latitude = location.getLatitude();
            final double longitude = location.getLongitude();
//            geoFire.setLocation("you", new GeoLocation(latitude, longitude),
//                    new GeoFire.CompletionListener() {
//                        @Override
//                        public void onComplete(String key, DatabaseError error) {
//                            if (mycurr != null) {
//                                mycurr.remove();
//                                mycurr = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
//                                        .title("you"));
//                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude)
//                                        , 12.0f));
//                            }
//                        }
//                    });
            LatLng latLngsrc = new LatLng(latitude, longitude);
            //move camera to my position
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngsrc, 10f));
        }
    }
    private void sendNotification(String raj, String content) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(allimpplaces.this)
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


//        Notification.Builder builder=new Notification.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title)
//                .setContentText(content);
//        NotificationManager manager=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
//        Intent intent=new Intent(this,MapsActivity.class);
//        PendingIntent contentIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);
//        builder.setContentText(contentIntent);
//        Notification notification=builder.build();
//        notification.flags !=Notification.FLAG_AUTO_CANCEL;
//        notification.defaults !=Notification.DEFAULT_SOUND;
//        manager.notify(new Random().nextInt().notification);
////


    }
    boolean network(){
        if ((wi != null && wi.isConnected()) || (mi != null && mi.isConnected())) {
            return true;
        } else {
            return false;
        }
    }
}