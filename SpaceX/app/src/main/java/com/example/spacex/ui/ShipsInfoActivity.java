package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.spacex.R;
import com.example.spacex.model.ShipsModel;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShipsInfoActivity extends AppCompatActivity {

    private ShipsModel shipsModel;
    private CircleImageView ShipsInfoPic, ShipsInfoUri;
    private TextView ShipsInfoNameTxt, ShipsInfoActiveTxt, ShipsInfoIdTxt, ShipsInfoTypeTxt, ShipsInfoYearBuiltTxt,
            ShipsInfoHomePortTxt, ShipsInfoWeightTxt, ShipsInfoMissionNameTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ships_info);

        shipsModel = (ShipsModel)getIntent().getSerializableExtra("Ships Object");


        ShipsInfoPic = findViewById(R.id.ShipsInfoPic);
        ShipsInfoNameTxt = findViewById(R.id.ShipsInfoNameTxt);
        ShipsInfoActiveTxt = findViewById(R.id.ShipsInfoActiveTxt);
        ShipsInfoIdTxt = findViewById(R.id.ShipsInfoIdTxt);
        ShipsInfoTypeTxt = findViewById(R.id.ShipsInfoTypeTxt);
        ShipsInfoYearBuiltTxt = findViewById(R.id.ShipsInfoYearBuiltTxt);
        ShipsInfoHomePortTxt = findViewById(R.id.ShipsInfoHomePortTxt);
        ShipsInfoWeightTxt = findViewById(R.id.ShipsInfoWeightTxt);
        ShipsInfoMissionNameTxt = findViewById(R.id.ShipsInfoMissionNameTxt);
        ShipsInfoUri = findViewById(R.id.ShipsInfoUri);


        Picasso.get().load(shipsModel.getImage()).placeholder(R.drawable.rocket).into(ShipsInfoPic);
        ShipsInfoNameTxt.setText(shipsModel.getShip_name());

        if(shipsModel.isActive()){
            ShipsInfoActiveTxt.setText("Active");
            ShipsInfoActiveTxt.setTextColor(Color.GREEN);}
        else{
            ShipsInfoActiveTxt.setText("Inactive");
            ShipsInfoActiveTxt.setTextColor(Color.RED);}

        ShipsInfoIdTxt.setText(shipsModel.getShip_id()+"");
        ShipsInfoTypeTxt.setText(shipsModel.getShip_type());
        ShipsInfoHomePortTxt.setText(shipsModel.getHome_port());
        ShipsInfoMissionNameTxt.setText(shipsModel.getMissions().get(0).getName());

        if(shipsModel.getYear_built() !=0)
        ShipsInfoYearBuiltTxt.setText(shipsModel.getYear_built()+"");
        else ShipsInfoYearBuiltTxt.setText("not available");
        if(shipsModel.getWeight_kg()!=0)
        ShipsInfoWeightTxt.setText(shipsModel.getWeight_kg()+" Kg");
        else ShipsInfoWeightTxt.setText("not available");


            ShipsInfoUri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(shipsModel.getUrl() !=null) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shipsModel.getUrl()));
                        startActivity(intent);
                    }
                    else Toast.makeText(ShipsInfoActivity.this,"URL not available",Toast.LENGTH_LONG).show();
                }
            });

    }
}