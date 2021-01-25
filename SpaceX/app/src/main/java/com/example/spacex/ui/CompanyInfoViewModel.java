package com.example.spacex.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.spacex.data.AppClient;
import com.example.spacex.model.CompanyInfoModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyInfoViewModel extends ViewModel {
    public MutableLiveData <CompanyInfoModel> CompanyInfoMutableLiveData = new MutableLiveData<>();

    public void getCompanyInfo(){
       getData().enqueue(new Callback<CompanyInfoModel>() {
            @Override
            public void onResponse(Call<CompanyInfoModel> call, Response<CompanyInfoModel> response) {
                CompanyInfoMutableLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<CompanyInfoModel> call, Throwable t) {
            }
        });
    }

    private Call<CompanyInfoModel> getData(){
        return AppClient.getInstance().getCompanyInfo();
    }

}
