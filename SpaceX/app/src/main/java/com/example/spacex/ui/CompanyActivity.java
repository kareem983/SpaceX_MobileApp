package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.spacex.R;
import com.example.spacex.model.CompanyInfoModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class CompanyActivity extends AppCompatActivity {

    private CompanyInfoViewModel companyInfoViewModel;
    private TextView foundedTxt, employeesTxt, valuationTxt, FounderName, CEOName, CTOName, Address, City, State, Description;
    private CircleImageView elonImg, flickrImg, twitterImg;
    private CompanyInfoModel companyInfoModel;
    private RelativeLayout bigTotal, total;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        bigTotal= (RelativeLayout)findViewById(R.id.bigTotal);
        total= (RelativeLayout)findViewById(R.id.total);
        progressBar = findViewById(R.id.progress);
        foundedTxt = findViewById(R.id.foundedID);
        employeesTxt = findViewById(R.id.employeesID);
        valuationTxt = findViewById(R.id.valuationID);
        elonImg = findViewById(R.id.elonWebID);
        flickrImg = findViewById(R.id.flickrWebID);
        twitterImg = findViewById(R.id.twitterWebID);
        FounderName = findViewById(R.id.founderNameTxt);
        CEOName = findViewById(R.id.CEONameTxt);
        CTOName = findViewById(R.id.CTONameTxt);
        Address = findViewById(R.id.companyAddressTxt);
        City = findViewById(R.id.companyCityTxt);
        State = findViewById(R.id.companyStateTxt);
        Description = findViewById(R.id.companySummaryTxt);

        companyInfoViewModel = ViewModelProviders.of(this).get(CompanyInfoViewModel.class);
        companyInfoViewModel.getCompanyInfo();
        companyInfoViewModel.CompanyInfoMutableLiveData.observe(this, new Observer<CompanyInfoModel>() {
            @Override
            public void onChanged(CompanyInfoModel companyInfo) {
                companyInfoModel = companyInfo;
                foundedTxt.setText(String.valueOf(companyInfo.getFounded()));
                employeesTxt.setText(String.valueOf(companyInfo.getEmployees()));
                valuationTxt.setText(String.valueOf(companyInfo.getValuation()));
                FounderName.setText(companyInfo.getFounder());
                CEOName.setText(companyInfo.getCeo());
                CTOName.setText(companyInfo.getCto_propulsion());
                Address.setText(companyInfo.getHeadquarters().getAddress());
                City.setText(companyInfo.getHeadquarters().getCity());
                State.setText(companyInfo.getHeadquarters().getState());
                Description.setText(companyInfo.getSummary());

                progressBar.setVisibility(View.GONE);
                bigTotal.setBackgroundColor(Color.BLACK);
                total.setVisibility(View.VISIBLE);

            }
        });

        elonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(companyInfoModel.getLinks().getElon_twitter()));
                startActivity(intent);
            }
        });

        flickrImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(companyInfoModel.getLinks().getFlickr()));
                startActivity(intent);
            }
        });

        twitterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(companyInfoModel.getLinks().getTwitter()));
                startActivity(intent);
            }
        });

    }
}