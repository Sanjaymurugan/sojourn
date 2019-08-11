package com.example.sanjaymurugan.sojournhappy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by RAJKUMAR on 17-03-2018.
 */

public interface IGoogleclient {
    @GET
    Call<pojo> getNearbypaces(@Url String url);
}
