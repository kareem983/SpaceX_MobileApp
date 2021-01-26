package com.example.spacex.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.spacex.data.AppClient;
import com.example.spacex.model.ShipsModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipsViewModel extends ViewModel {

    public MutableLiveData<List<ShipsModel>> ShipsMutableLiveData = new MutableLiveData<>();

    public void getShips(){
        getData().enqueue(new Callback<List<ShipsModel>>() {
            @Override
            public void onResponse(Call<List<ShipsModel>> call, Response<List<ShipsModel>> response) {
                ShipsMutableLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<ShipsModel>> call, Throwable t) {
            }
        });
    }

    private Call<List<ShipsModel>> getData(){
        return AppClient.getInstance().getShips();
    }

}
