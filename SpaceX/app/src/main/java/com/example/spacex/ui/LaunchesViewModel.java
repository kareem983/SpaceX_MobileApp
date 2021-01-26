package com.example.spacex.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.spacex.data.AppClient;
import com.example.spacex.model.LaunchesModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaunchesViewModel extends ViewModel {

    public MutableLiveData<List<LaunchesModel>> LaunchesMutableLiveData = new MutableLiveData<>();

    public void getLaunches(){
        getData().enqueue(new Callback<List<LaunchesModel>>() {
            @Override
            public void onResponse(Call<List<LaunchesModel>> call, Response<List<LaunchesModel>> response) {
                LaunchesMutableLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<LaunchesModel>> call, Throwable t) {
            }
        });
    }

    private Call<List<LaunchesModel>> getData(){
        return AppClient.getInstance().getLaunches();
    }

}
