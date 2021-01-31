package com.example.spacex.data;

import com.example.spacex.model.CompanyInfoModel;
import com.example.spacex.model.DragonsModel;
import com.example.spacex.model.LaunchesModel;
import com.example.spacex.model.RocketsModel;
import com.example.spacex.model.ShipsModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {
    private static final String BASE_URL = "https://api.spacexdata.com/v3/";
    private static AppClient instance;
    private AppApiInterface appApiInterface;


    public AppClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        appApiInterface = retrofit.create(AppApiInterface.class);
    }


    public static AppClient getInstance(){
        if(instance == null) return new AppClient();
        return instance;
    }


    public Call<CompanyInfoModel> getCompanyInfo(){ return appApiInterface.getCompanyInfo();}

    public Call<List<RocketsModel>> getRockets(){
        return appApiInterface.getRockets();
    }

    public Call<List<LaunchesModel>> getLaunches(){
        return appApiInterface.getLaunches();
    }

    public Call<List<DragonsModel>> getDragons(){
        return appApiInterface.getDragons();
    }

    public Call<List<ShipsModel>> getShips(){
        return appApiInterface.getShips();
    }

}
