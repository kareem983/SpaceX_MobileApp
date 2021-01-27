package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.spacex.R;
import com.example.spacex.model.RocketsModel;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class RocketInfoActivity extends AppCompatActivity {

    private RocketsModel rocketsModel;
    private CircleImageView RocketImage, RocketWikipediaTxt;
    private TextView RocketName, RocketActiveTxt, RocketIdTxt, RocketWeightTxt, RocketCountryTxt, RocketFirstFTxt, RocketCostTxt
            ,RocketHeightTxt, RocketEngineNumTxt, RocketEngineTypeTxt, RocketEngineVersionTxt, RocketDescTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rocket_info);

        rocketsModel = (RocketsModel)getIntent().getSerializableExtra("Rocket Object");

        RocketImage = findViewById(R.id.RocketInfoPic);
        RocketName = findViewById(R.id.RocketInfoNameTxt);
        RocketActiveTxt = findViewById(R.id.RocketInfoActiveTxt);
        RocketIdTxt = findViewById(R.id.RocketInfoIdTxt);
        RocketWeightTxt = findViewById(R.id.RocketInfoMassTxt);
        RocketCountryTxt = findViewById(R.id.RocketInfoCountryTxt);
        RocketFirstFTxt = findViewById(R.id.RocketInfoFirstFlightTxt);
        RocketCostTxt = findViewById(R.id.RocketInfoCostLaunchTxt);
        RocketHeightTxt = findViewById(R.id.RocketInfoRocketHeightTxt);
        RocketEngineNumTxt = findViewById(R.id.RocketInfoRocketEngineNumTxt);
        RocketEngineTypeTxt = findViewById(R.id.RocketInfoRocketEngineTypeTxt);
        RocketEngineVersionTxt = findViewById(R.id.RocketInfoRocketEngineVersionTxt);
        RocketDescTxt =findViewById(R.id.RocketInfoDescTxt);
        RocketWikipediaTxt = findViewById(R.id.RocketInfoWiki);

        Picasso.get().load(rocketsModel.getFlickr_images().get(0)).placeholder(R.drawable.rocket).into(RocketImage);
        RocketName.setText(rocketsModel.getRocket_name());
        if(rocketsModel.getActive()){
            RocketActiveTxt.setText("Active");
            RocketActiveTxt.setTextColor(Color.GREEN);}
        else{
            RocketActiveTxt.setText("Inactive");
            RocketActiveTxt.setTextColor(Color.RED);}

        RocketIdTxt.setText(String.valueOf(rocketsModel.getId()));
        RocketWeightTxt.setText(rocketsModel.getMass().getKg()+ " Kg");
        RocketCountryTxt.setText(rocketsModel.getCountry());
        RocketFirstFTxt.setText(rocketsModel.getFirst_flight());
        RocketCostTxt.setText(rocketsModel.getCost_per_launch()+" $");
        RocketHeightTxt.setText(rocketsModel.getHeight().getMeters()+" m");
        RocketEngineNumTxt.setText(String.valueOf(rocketsModel.getEngines().getNumber()));
        RocketEngineTypeTxt.setText(rocketsModel.getEngines().getType());
        RocketDescTxt.setText(rocketsModel.getDescription());

        if(!rocketsModel.getEngines().getVersion().isEmpty())
            RocketEngineVersionTxt.setText(rocketsModel.getEngines().getVersion());
        else RocketEngineVersionTxt.setText("not available");

        RocketWikipediaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(rocketsModel.getWikipedia()));
                startActivity(intent);
            }
        });
    }
}