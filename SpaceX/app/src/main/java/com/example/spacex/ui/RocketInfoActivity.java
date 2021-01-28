package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.example.spacex.R;
import com.example.spacex.databinding.ActivityRocketInfoBinding;
import com.example.spacex.model.RocketsModel;
import com.squareup.picasso.Picasso;

public class RocketInfoActivity extends AppCompatActivity {

    private ActivityRocketInfoBinding binding;
    private RocketsModel rocketsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRocketInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rocketsModel = (RocketsModel)getIntent().getSerializableExtra("Rocket Object");

        Picasso.get().load(rocketsModel.getFlickr_images().get(0)).placeholder(R.drawable.rocket).into(binding.RocketInfoPic);
        binding.RocketInfoNameTxt.setText(rocketsModel.getRocket_name());
        if(rocketsModel.getActive()){
            binding.RocketInfoActiveTxt.setText("Active");
            binding.RocketInfoActiveTxt.setTextColor(Color.GREEN);}
        else{
            binding.RocketInfoActiveTxt.setText("Inactive");
            binding.RocketInfoActiveTxt.setTextColor(Color.RED);}

        binding.RocketInfoIdTxt.setText(String.valueOf(rocketsModel.getId()));
        binding.RocketInfoMassTxt.setText(rocketsModel.getMass().getKg()+ " Kg");
        binding.RocketInfoCountryTxt.setText(rocketsModel.getCountry());
        binding.RocketInfoFirstFlightTxt.setText(rocketsModel.getFirst_flight());
        binding.RocketInfoCostLaunchTxt.setText(rocketsModel.getCost_per_launch()+" $");
        binding.RocketInfoRocketHeightTxt.setText(rocketsModel.getHeight().getMeters()+" m");
        binding.RocketInfoRocketEngineNumTxt.setText(String.valueOf(rocketsModel.getEngines().getNumber()));
        binding.RocketInfoRocketEngineTypeTxt.setText(rocketsModel.getEngines().getType());
        binding.RocketInfoDescTxt.setText(rocketsModel.getDescription());

        if(!rocketsModel.getEngines().getVersion().isEmpty())
             binding.RocketInfoRocketEngineVersionTxt.setText(rocketsModel.getEngines().getVersion());
        else binding.RocketInfoRocketEngineVersionTxt.setText("not available");

        binding.RocketInfoWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(rocketsModel.getWikipedia()));
                startActivity(intent);
            }
        });
    }
}