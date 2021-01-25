package com.example.spacex.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.spacex.data.AppClient;
import com.example.spacex.model.RocketsModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RocketsViewModel extends ViewModel {

    public MutableLiveData<List<RocketsModel>> RocketsMutableLiveData = new MutableLiveData<>();

    public void getRockets(){
        getData().enqueue(new Callback<List<RocketsModel>>() {
            @Override
            public void onResponse(Call<List<RocketsModel>> call, Response<List<RocketsModel>> response) {
                RocketsMutableLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<RocketsModel>> call, Throwable t) {
            }
        });
    }

    private Call<List<RocketsModel>> getData(){
        return AppClient.getInstance().getRockets();
    }

}
