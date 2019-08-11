package com.example.sanjaymurugan.sojournhappy;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RAJKUMAR on 17-03-2018.
 */

public class Retrofitservice {
    private static Retrofit retrofit=null;
    public static Retrofit getclient(String baseurl){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
