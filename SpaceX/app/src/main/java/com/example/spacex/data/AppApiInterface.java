package com.example.spacex.data;

import com.example.spacex.model.CompanyInfoModel;
import com.example.spacex.model.DragonsModel;
import com.example.spacex.model.LaunchesModel;
import com.example.spacex.model.RocketsModel;
import com.example.spacex.model.ShipsModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AppApiInterface {

    @GET("info")
    public Call<CompanyInfoModel> getCompanyInfo();

    @GET("rockets")
    public Call<List<RocketsModel>> getRockets();

    @GET("launches")
    public Call<List<LaunchesModel>> getLaunches();

    @GET("dragons")
    public Call<List<DragonsModel>> getDragons();

    @GET("ships")
    public Call<List<ShipsModel>> getShips();

}
