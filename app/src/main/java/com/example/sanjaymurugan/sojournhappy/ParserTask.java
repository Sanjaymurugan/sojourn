package com.example.sanjaymurugan.sojournhappy;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by RAJKUMAR on 15-03-2018.
 */

public class ParserTask extends AsyncTask<String,Integer,List<List<HashMap<String,String>>>> {
    private String distance="";
    private String duration="";

    @Override
    protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
        JSONObject jsonObject;
//        String distance="";
//        String duration="";
        List<List<HashMap<String,String>>> routes=null;
        try{
            jsonObject=new JSONObject(jsonData[0]);
            DirectionsJSONParser parser=new DirectionsJSONParser();
            routes=parser.parse(jsonObject);
        }
        catch (Exception e){
            System.err.println("--->"+e);
        }
        return routes;
    }

    @Override
    protected void onPostExecute(List<List<HashMap<String, String>>> result) {
        ArrayList<LatLng> points=null;
        PolylineOptions lineoptions=null;
        for(int i=0;i<result.size();i++){
            points=new ArrayList<LatLng>();
            lineoptions=new PolylineOptions();
            List<HashMap<String,String>> path=result.get(i);
            for(int j=0;j<path.size();j++){
                HashMap<String,String> point=path.get(j);
                if(j==0){
                     distance = (String)point.get("distance");
                     continue;
                }
                else if(j==1){
                    duration=(String)point.get("duration");
                    continue;

                }
                double lat=Double.parseDouble(point.get("lat"));
                double lng=Double.parseDouble(point.get("lng"));
                LatLng position=new LatLng(lat,lng);
                points.add(position);
            }
            lineoptions.addAll(points);
            lineoptions.width(12);
            lineoptions.color(Color.BLUE);
        }
        if(lineoptions!=null) {
            allimpplaces allimp = new allimpplaces();
            allimp.distancemap.setText("distance: " + distance);
            allimp.duration.setText("duration: " + duration);
            allimp.mMap.addPolyline(lineoptions);
        }
    }
}