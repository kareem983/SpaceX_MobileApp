package com.example.spacex.adapter;

import android.content.Context;
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

    private List<RocketsModel> RocketsModelsArrayList = new ArrayList<>();
    private Context context;

    public RocketsAdapter(Context context, List<RocketsModel> RocketsModelsArrayList) {
        this.context = context;
        this.RocketsModelsArrayList = RocketsModelsArrayList;
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
        RocketsModel rocketsModel = RocketsModelsArrayList.get(position);
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
            holder.RocketActiveTxt.setText("inActive");
            holder.RocketActiveTxt.setTextColor(Color.RED);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
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
        }
    }
}
