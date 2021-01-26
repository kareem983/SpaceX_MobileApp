package com.example.spacex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spacex.R;
import com.example.spacex.model.LaunchesModel;
import com.example.spacex.model.RocketsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LaunchesAdapter extends RecyclerView.Adapter<LaunchesAdapter.ViewHolder> {

    private List<LaunchesModel> LaunchesModelsArrayList = new ArrayList<>();
    private Context context;

    public LaunchesAdapter(Context context, List<LaunchesModel> LaunchesModelsArrayList) {
        this.context = context;
        this.LaunchesModelsArrayList = LaunchesModelsArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.launche_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return LaunchesModelsArrayList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LaunchesModel launchesModel = LaunchesModelsArrayList.get(position);
        if(launchesModel.getLinks().getMission_patch_small() !=null)
        Picasso.get().load(launchesModel.getLinks().getMission_patch_small()).placeholder(R.drawable.rocket).into(holder.LaunchImg);
        else holder.LaunchImg.setImageResource(R.drawable.rocket);

        holder.LaunchName.setText(launchesModel.getMission_name());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView LaunchImg;
        private TextView LaunchName, LaunchShowDetailsName;
        private ImageButton shareLaunchImtBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            LaunchImg = itemView.findViewById(R.id.LaunchImg);
            LaunchName = itemView.findViewById(R.id.LaunchName);
            LaunchShowDetailsName = itemView.findViewById(R.id.LaunchShowDetailsName);
            shareLaunchImtBtn = itemView.findViewById(R.id.shareLaunchImtBtn);
        }
    }
}
