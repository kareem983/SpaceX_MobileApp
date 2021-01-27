package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.spacex.R;
import com.example.spacex.model.LaunchesModel;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class LaunchesInfoActivity extends AppCompatActivity {

    private LaunchesModel launchesModel;
    private CircleImageView LaunchesInfoPic, LaunchesInfoWiki, LaunchesInfoYoutube;
    private TextView  LaunchesInfoNameTxt, LaunchesInfoUpcomingTxt, LaunchesInfoFlightNumTxt, LaunchesInfoLaunchYearTxt,
            LaunchesInfoPrecisionTxt, LaunchesInfoRocketIdTxt, LaunchesInfoRocketNameTxt,LaunchesInfoRocketTypeTxt
            ,LaunchesInfoDetailsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launche_info);

        launchesModel = (LaunchesModel)getIntent().getSerializableExtra("Launches Object");


        LaunchesInfoPic = findViewById(R.id.LaunchesInfoPic);
        LaunchesInfoNameTxt = findViewById(R.id.LaunchesInfoNameTxt);
        LaunchesInfoUpcomingTxt = findViewById(R.id.LaunchesInfoUpcomingTxt);
        LaunchesInfoFlightNumTxt = findViewById(R.id.LaunchesInfoFlightNumTxt);
        LaunchesInfoLaunchYearTxt = findViewById(R.id.LaunchesInfoLaunchYearTxt);
        LaunchesInfoPrecisionTxt = findViewById(R.id.LaunchesInfoPrecisionTxt);
        LaunchesInfoRocketIdTxt = findViewById(R.id.LaunchesInfoRocketIdTxt);
        LaunchesInfoRocketNameTxt = findViewById(R.id.LaunchesInfoRocketNameTxt);
        LaunchesInfoRocketTypeTxt = findViewById(R.id.LaunchesInfoRocketTypeTxt);
        LaunchesInfoDetailsTxt = findViewById(R.id.LaunchesInfoDetailsTxt);
        LaunchesInfoWiki = findViewById(R.id.LaunchesInfoWiki);
        LaunchesInfoYoutube = findViewById(R.id.LaunchesInfoYoutube);


        Picasso.get().load(launchesModel.getLinks().getMission_patch_small()).placeholder(R.drawable.rocket).into(LaunchesInfoPic);
        LaunchesInfoNameTxt.setText(launchesModel.getMission_name());

        if(launchesModel.isUpcoming()){
            LaunchesInfoUpcomingTxt.setText("Upcoming");
            LaunchesInfoUpcomingTxt.setTextColor(Color.GREEN);}
        else{
            LaunchesInfoUpcomingTxt.setText("Not coming");
            LaunchesInfoUpcomingTxt.setTextColor(Color.RED);}

        LaunchesInfoFlightNumTxt.setText(launchesModel.getFlight_number()+"");
        LaunchesInfoLaunchYearTxt.setText(launchesModel.getLaunch_year()+"");
        LaunchesInfoPrecisionTxt.setText(launchesModel.getTentative_max_precision());
        LaunchesInfoRocketIdTxt.setText(launchesModel.getRocket().getRocket_id()+"");
        LaunchesInfoRocketNameTxt.setText(launchesModel.getRocket().getRocket_name());
        LaunchesInfoRocketTypeTxt.setText(launchesModel.getRocket().getRocket_type());

        if(launchesModel.getDetails() != null)
        LaunchesInfoDetailsTxt.setText(launchesModel.getDetails());
        else LaunchesInfoDetailsTxt.setText("Not available description");

        LaunchesInfoWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(launchesModel.getLinks().getWikipedia()));
                startActivity(intent);
            }
        });

        LaunchesInfoYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(launchesModel.getLinks().getVideo_link()));
                startActivity(intent);
            }
        });


    }
}