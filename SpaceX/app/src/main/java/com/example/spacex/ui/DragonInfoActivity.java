package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.example.spacex.R;
import com.example.spacex.databinding.ActivityDragonInfoBinding;
import com.example.spacex.model.DragonsModel;
import com.squareup.picasso.Picasso;

public class DragonInfoActivity extends AppCompatActivity {

    private ActivityDragonInfoBinding binding;
    private DragonsModel dragonsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDragonInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dragonsModel = (DragonsModel)getIntent().getSerializableExtra("Dragon Object");


        Picasso.get().load(dragonsModel.getFlickr_images().get(0)).placeholder(R.drawable.rocket).into(binding.DragonsInfoPic);
        binding.DragonsInfoNameTxt.setText(dragonsModel.getName());

        if(dragonsModel.isActive()){
            binding.DragonsInfoActiveTxt.setText("Active");
            binding.DragonsInfoActiveTxt.setTextColor(Color.GREEN);}
        else{
            binding.DragonsInfoActiveTxt.setText("Inactive");
            binding.DragonsInfoActiveTxt.setTextColor(Color.RED);}

        binding.DragonsInfoIdTxt.setText(dragonsModel.getId()+"");
        binding.DragonsInfoTypeTxt.setText(dragonsModel.getType());
        binding.DragonsInfoFirstFlightTxt.setText(dragonsModel.getFirst_flight());
        binding.DragonsInfoHeightTxt.setText(dragonsModel.getHeight_w_trunk().getMeters()+" m");
        binding.DragonsInfoWeightTxt.setText(dragonsModel.getDry_mass_kg()+" Kg");
        binding.DragonsInfoDiameterTxt.setText(dragonsModel.getDiameter().getMeters()+" m");
        binding.DragonsInfoDetailsTxt.setText(dragonsModel.getDescription());

        binding.DragonsInfoWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dragonsModel.getWikipedia()));
                startActivity(intent);
            }
        });

    }
}