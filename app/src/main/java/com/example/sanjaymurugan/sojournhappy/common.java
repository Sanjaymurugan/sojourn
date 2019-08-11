package com.example.sanjaymurugan.sojournhappy;

/**
 * Created by RAJKUMAR on 17-03-2018.
 */

public class common {
    private static final String GOOGLEAPIURL="https://maps.googleapis.com/";
    public  static IGoogleclient getgoogleclient(){
        return Retrofitservice.getclient(GOOGLEAPIURL).create(IGoogleclient.class);
    }
}
