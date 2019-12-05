package com.example.android_demo_app.Api;

import com.example.android_demo_app.ModelClass.PlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("facts.json")
    Call<PlaceResponse> getPlaceDetails();
}
