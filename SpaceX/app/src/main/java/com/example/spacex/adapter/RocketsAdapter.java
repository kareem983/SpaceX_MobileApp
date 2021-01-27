package com.example.spacex.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.spacex.R;
import com.example.spacex.model.RocketsModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class RocketsAdapter extends RecyclerView.Adapter<RocketsAdapter.ViewHolder> {
    private RecyclerViewClickListener listener;
    private List<RocketsModel> RocketsModelsArrayList = new ArrayList<>();
    private Context context;

    public RocketsAdapter(Context context, List<RocketsModel> RocketsModelsArrayList, RecyclerViewClickListener listener) {
        this.context = context;
        this.RocketsModelsArrayList = RocketsModelsArrayList;
        this.listener =listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.rocket_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return RocketsModelsArrayList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RocketsModel rocketsModel = RocketsModelsArrayList.get(position);
        if(rocketsModel.getFlickr_images().get(0) !=null)
        Picasso.get().load(rocketsModel.getFlickr_images().get(0)).placeholder(R.drawable.rocket).into(holder.RocketImg);
        else holder.RocketImg.setImageResource(R.drawable.rocket);
        holder.RocketName.setText(rocketsModel.getRocket_name());

        if(rocketsModel.getActive()) {
            holder.RocketActiveImg.setImageResource(R.drawable.active);
            holder.RocketActiveTxt.setText("Active");
            holder.RocketActiveTxt.setTextColor(Color.GREEN);
        }
        else {
            holder.RocketActiveImg.setImageResource(R.drawable.inactive);
            holder.RocketActiveTxt.setText("Inactive");
            holder.RocketActiveTxt.setTextColor(Color.RED);
        }

        holder.shareRocketsImtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Rocket Name: "+rocketsModel.getRocket_name()
                     +"\nRocket weight is "+rocketsModel.getMass().getKg()+" Kg\nFirst Flight: "+rocketsModel.getFirst_flight()
                +"\nManifacturing Country is "+rocketsModel.getCountry()+"\nManifacturing Company is "+rocketsModel.getCompany()
                +"\nWikipedia Link: "+rocketsModel.getWikipedia());

                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);

            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CircleImageView RocketImg;
        private TextView RocketName, RocketActiveTxt, RocketShowDetailsName;
        private ImageView RocketActiveImg;
        private ImageButton shareRocketsImtBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            RocketImg = itemView.findViewById(R.id.RocketImg);
            RocketName = itemView.findViewById(R.id.RocketName);
            RocketActiveTxt = itemView.findViewById(R.id.RocketActiveTxt);
            RocketShowDetailsName = itemView.findViewById(R.id.RocketShowDetailsName);
            RocketActiveImg = itemView.findViewById(R.id.RocketActiveImg);
            shareRocketsImtBtn = itemView.findViewById(R.id.shareRocketsImtBtn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        public void onClick(View view, int position);
    }

}
