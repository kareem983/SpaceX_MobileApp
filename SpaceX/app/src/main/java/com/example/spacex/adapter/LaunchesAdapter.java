package com.example.spacex.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.spacex.R;
import com.example.spacex.model.LaunchesModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class LaunchesAdapter extends RecyclerView.Adapter<LaunchesAdapter.ViewHolder> {
    private RecyclerViewClickListener listener;
    private List<LaunchesModel> LaunchesModelsArrayList = new ArrayList<>();
    private Context context;

    public LaunchesAdapter(Context context, List<LaunchesModel> LaunchesModelsArrayList, RecyclerViewClickListener listener) {
        this.context = context;
        this.LaunchesModelsArrayList = LaunchesModelsArrayList;
        this.listener = listener;
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
        final LaunchesModel launchesModel = LaunchesModelsArrayList.get(position);
        if(launchesModel.getLinks().getMission_patch_small() !=null)
        Picasso.get().load(launchesModel.getLinks().getMission_patch_small()).placeholder(R.drawable.rocket).into(holder.LaunchImg);
        else holder.LaunchImg.setImageResource(R.drawable.rocket);

        holder.LaunchName.setText(launchesModel.getMission_name());

        holder.shareLaunchImtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Mission Name: "+launchesModel.getMission_name()
                        +"\nLaunch year is "+launchesModel.getLaunch_year()+"\nThe Flight number is: "+launchesModel.getFlight_number()
                        +"\nLaunched with rocket name "+launchesModel.getRocket().getRocket_name()+"\nSome Links: "+
                        launchesModel.getLinks().getWikipedia()+"\n"+launchesModel.getLinks().getVideo_link());

                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CircleImageView LaunchImg;
        private TextView LaunchName, LaunchShowDetailsName;
        private ImageButton shareLaunchImtBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            LaunchImg = itemView.findViewById(R.id.LaunchImg);
            LaunchName = itemView.findViewById(R.id.LaunchName);
            LaunchShowDetailsName = itemView.findViewById(R.id.LaunchShowDetailsName);
            shareLaunchImtBtn = itemView.findViewById(R.id.shareLaunchImtBtn);
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
