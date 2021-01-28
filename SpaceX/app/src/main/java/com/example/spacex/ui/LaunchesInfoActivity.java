package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.spacex.R;
import com.example.spacex.databinding.ActivityLauncheInfoBinding;
import com.example.spacex.model.LaunchesModel;
import com.squareup.picasso.Picasso;

public class LaunchesInfoActivity extends AppCompatActivity {

    private ActivityLauncheInfoBinding binding;
    private LaunchesModel launchesModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLauncheInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        launchesModel = (LaunchesModel)getIntent().getSerializableExtra("Launches Object");

        Picasso.get().load(launchesModel.getLinks().getMission_patch_small()).placeholder(R.drawable.rocket).into(binding.LaunchesInfoPic);
        binding.LaunchesInfoNameTxt.setText(launchesModel.getMission_name());

        if(launchesModel.isUpcoming()){
            binding.LaunchesInfoUpcomingTxt.setText("Upcoming");
            binding.LaunchesInfoUpcomingTxt.setTextColor(Color.GREEN);}
        else{
            binding.LaunchesInfoUpcomingTxt.setText("Not coming");
            binding.LaunchesInfoUpcomingTxt.setTextColor(Color.RED);}

        binding.LaunchesInfoFlightNumTxt.setText(launchesModel.getFlight_number()+"");
        binding.LaunchesInfoLaunchYearTxt.setText(launchesModel.getLaunch_year()+"");
        binding.LaunchesInfoPrecisionTxt.setText(launchesModel.getTentative_max_precision());
        binding.LaunchesInfoRocketIdTxt.setText(launchesModel.getRocket().getRocket_id()+"");
        binding.LaunchesInfoRocketNameTxt.setText(launchesModel.getRocket().getRocket_name());
        binding.LaunchesInfoRocketTypeTxt.setText(launchesModel.getRocket().getRocket_type());

        if(launchesModel.getDetails() != null)
             binding.LaunchesInfoDetailsTxt.setText(launchesModel.getDetails());
        else binding.LaunchesInfoDetailsTxt.setText("Not available description");

        binding.LaunchesInfoWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(launchesModel.getLinks().getWikipedia() !=null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(launchesModel.getLinks().getWikipedia()));
                    startActivity(intent);}
                else Toast.makeText(LaunchesInfoActivity.this,"URL not available",Toast.LENGTH_LONG).show();

            }
        });

        binding.LaunchesInfoYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(launchesModel.getLinks().getVideo_link() !=null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(launchesModel.getLinks().getVideo_link()));
                    startActivity(intent);
                }
                else Toast.makeText(LaunchesInfoActivity.this,"URL not available",Toast.LENGTH_LONG).show();
            }
        });

    }
}