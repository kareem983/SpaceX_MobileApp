package com.example.spacex;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("launches/1")
    public Call<LaunchesModel> getPost();
}
