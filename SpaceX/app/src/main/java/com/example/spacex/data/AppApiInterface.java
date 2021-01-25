package com.example.spacex.data;

import com.example.spacex.model.CompanyInfoModel;
import com.example.spacex.model.RocketsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppApiInterface {

    @GET("info")
    public Call<CompanyInfoModel> getCompanyInfo();

    @GET("rockets")
    public Call<List<RocketsModel>> getRockets();

}
