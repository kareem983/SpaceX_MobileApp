package com.example.spacex.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.spacex.data.AppClient;
import com.example.spacex.model.DragonsModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DragonsViewModel extends ViewModel {

    public MutableLiveData<List<DragonsModel>> DragonsMutableLiveData = new MutableLiveData<>();

    public void getDragons(){
        getData().enqueue(new Callback<List<DragonsModel>>() {
            @Override
            public void onResponse(Call<List<DragonsModel>> call, Response<List<DragonsModel>> response) {
                DragonsMutableLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<DragonsModel>> call, Throwable t) {
            }
        });
    }

    private Call<List<DragonsModel>> getData(){
        return AppClient.getInstance().getDragons();
    }

}
