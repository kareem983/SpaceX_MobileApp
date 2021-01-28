package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.example.spacex.databinding.ActivityCompanyBinding;
import com.example.spacex.model.CompanyInfoModel;

public class CompanyActivity extends AppCompatActivity {

    private ActivityCompanyBinding binding;
    private CompanyInfoViewModel companyInfoViewModel;
    private CompanyInfoModel companyInfoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCompanyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        companyInfoViewModel = ViewModelProviders.of(this).get(CompanyInfoViewModel.class);
        companyInfoViewModel.getCompanyInfo();
        companyInfoViewModel.CompanyInfoMutableLiveData.observe(this, new Observer<CompanyInfoModel>() {
            @Override
            public void onChanged(CompanyInfoModel companyInfo) {
                companyInfoModel = companyInfo;
                binding.CompanyFoundedID.setText(String.valueOf(companyInfo.getFounded()));
                binding.CompanyEmployeesID.setText(String.valueOf(companyInfo.getEmployees()));
                binding.CompanyValuationID.setText(String.valueOf(companyInfo.getValuation()));
                binding.CompanyFounderNameTxt.setText(companyInfo.getFounder());
                binding.CompanyCEONameTxt.setText(companyInfo.getCeo());
                binding.CompanyCTONameTxt.setText(companyInfo.getCto_propulsion());
                binding.CompanyAddressTxt.setText(companyInfo.getHeadquarters().getAddress());
                binding.CompanyCityTxt.setText(companyInfo.getHeadquarters().getCity());
                binding.CompanyStateTxt.setText(companyInfo.getHeadquarters().getState());
                binding.CompanySummaryTxt.setText(companyInfo.getSummary());

                binding.CompanyProgress.setVisibility(View.GONE);
                binding.CompanyBigTotal.setBackgroundColor(Color.BLACK);
                binding.CompanyTotal.setVisibility(View.VISIBLE);

            }
        });

        binding.CompanyElonWebID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(companyInfoModel.getLinks().getElon_twitter()));
                startActivity(intent);
            }
        });

        binding.CompanyFlickrWebID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(companyInfoModel.getLinks().getFlickr()));
                startActivity(intent);
            }
        });

        binding.CompanyTwitterWebID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(companyInfoModel.getLinks().getTwitter()));
                startActivity(intent);
            }
        });

    }
}