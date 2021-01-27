package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.spacex.R;
import com.example.spacex.model.DragonsModel;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class DragonInfoActivity extends AppCompatActivity {

    private DragonsModel dragonsModel;
    private CircleImageView DragonsInfoPic, DragonsInfoWiki;
    private TextView DragonsInfoNameTxt, DragonsInfoActiveTxt, DragonsInfoIdTxt, DragonsInfoTypeTxt, DragonsInfoFirstFlightTxt,
            DragonsInfoHeightTxt, DragonsInfoWeightTxt, DragonsInfoDiameterTxt, DragonsInfoDetailsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragon_info);

        dragonsModel = (DragonsModel)getIntent().getSerializableExtra("Dragon Object");

        DragonsInfoPic = findViewById(R.id.DragonsInfoPic);
        DragonsInfoNameTxt = findViewById(R.id.DragonsInfoNameTxt);
        DragonsInfoActiveTxt = findViewById(R.id.DragonsInfoActiveTxt);
        DragonsInfoIdTxt = findViewById(R.id.DragonsInfoIdTxt);
        DragonsInfoTypeTxt = findViewById(R.id.DragonsInfoTypeTxt);
        DragonsInfoFirstFlightTxt = findViewById(R.id.DragonsInfoFirstFlightTxt);
        DragonsInfoHeightTxt = findViewById(R.id.DragonsInfoHeightTxt);
        DragonsInfoWeightTxt = findViewById(R.id.DragonsInfoWeightTxt);
        DragonsInfoDiameterTxt = findViewById(R.id.DragonsInfoDiameterTxt);
        DragonsInfoDetailsTxt = findViewById(R.id.DragonsInfoDetailsTxt);
        DragonsInfoWiki = findViewById(R.id.DragonsInfoWiki);


        Picasso.get().load(dragonsModel.getFlickr_images().get(0)).placeholder(R.drawable.rocket).into(DragonsInfoPic);
        DragonsInfoNameTxt.setText(dragonsModel.getName());

        if(dragonsModel.isActive()){
            DragonsInfoActiveTxt.setText("Active");
            DragonsInfoActiveTxt.setTextColor(Color.GREEN);}
        else{
            DragonsInfoActiveTxt.setText("Inactive");
            DragonsInfoActiveTxt.setTextColor(Color.RED);}

        DragonsInfoIdTxt.setText(dragonsModel.getId()+"");
        DragonsInfoTypeTxt.setText(dragonsModel.getType());
        DragonsInfoFirstFlightTxt.setText(dragonsModel.getFirst_flight());
        DragonsInfoHeightTxt.setText(dragonsModel.getHeight_w_trunk().getMeters()+" m");
        DragonsInfoWeightTxt.setText(dragonsModel.getDry_mass_kg()+" Kg");
        DragonsInfoDiameterTxt.setText(dragonsModel.getDiameter().getMeters()+" m");
        DragonsInfoDetailsTxt.setText(dragonsModel.getDescription());

        DragonsInfoWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dragonsModel.getWikipedia()));
                startActivity(intent);
            }
        });

    }
}