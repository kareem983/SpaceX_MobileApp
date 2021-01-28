package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.spacex.R;
import com.example.spacex.databinding.ActivityShipsInfoBinding;
import com.example.spacex.model.ShipsModel;
import com.squareup.picasso.Picasso;
public class ShipsInfoActivity extends AppCompatActivity {

    private ActivityShipsInfoBinding binding;
    private ShipsModel shipsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivityShipsInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shipsModel = (ShipsModel)getIntent().getSerializableExtra("Ships Object");

        Picasso.get().load(shipsModel.getImage()).placeholder(R.drawable.rocket).into(binding.ShipsInfoPic);
        binding.ShipsInfoNameTxt.setText(shipsModel.getShip_name());

        if(shipsModel.isActive()){
            binding.ShipsInfoActiveTxt.setText("Active");
            binding.ShipsInfoActiveTxt.setTextColor(Color.GREEN);}
        else{
            binding.ShipsInfoActiveTxt.setText("Inactive");
            binding.ShipsInfoActiveTxt.setTextColor(Color.RED);}

        binding.ShipsInfoIdTxt.setText(shipsModel.getShip_id()+"");
        binding.ShipsInfoTypeTxt.setText(shipsModel.getShip_type());
        binding.ShipsInfoHomePortTxt.setText(shipsModel.getHome_port());

        if(shipsModel.getMissions().size()>0)
             binding.ShipsInfoMissionNameTxt.setText(shipsModel.getMissions().get(0).getName());
        else binding.ShipsInfoMissionNameTxt.setText("not available");

        if(shipsModel.getYear_built() !=0)
             binding.ShipsInfoYearBuiltTxt.setText(shipsModel.getYear_built()+"");
        else binding.ShipsInfoYearBuiltTxt.setText("not available");
        if(shipsModel.getWeight_kg()!=0)
             binding.ShipsInfoWeightTxt.setText(shipsModel.getWeight_kg()+" Kg");
        else binding.ShipsInfoWeightTxt.setText("not available");


        binding.ShipsInfoUri.setOnClickListener(new View.OnClickListener() {
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