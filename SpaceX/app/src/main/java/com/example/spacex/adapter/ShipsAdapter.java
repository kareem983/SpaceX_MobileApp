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
import com.example.spacex.model.ShipsModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShipsAdapter extends RecyclerView.Adapter<ShipsAdapter.ViewHolder> {

    private List<ShipsModel> ShipsModelsArrayList = new ArrayList<>();
    private Context context;

    public ShipsAdapter(Context context, List<ShipsModel> ShipsModelsArrayList) {
        this.context = context;
        this.ShipsModelsArrayList = ShipsModelsArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.ship_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return ShipsModelsArrayList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShipsModel shipsModel = ShipsModelsArrayList.get(position);
        if(shipsModel.getImage() !=null)
            Picasso.get().load(shipsModel.getImage()).placeholder(R.drawable.rocket).into(holder.ShipImg);
        else holder.ShipImg.setImageResource(R.drawable.rocket);
        holder.ShipName.setText(shipsModel.getShip_name());

        if(shipsModel.isActive()) {
            holder.ShipActiveImg.setImageResource(R.drawable.active);
            holder.ShipActiveTxt.setText("Active");
            holder.ShipActiveTxt.setTextColor(Color.GREEN);
        }
        else {
            holder.ShipActiveImg.setImageResource(R.drawable.inactive);
            holder.ShipActiveTxt.setText("inActive");
            holder.ShipActiveTxt.setTextColor(Color.RED);
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView ShipImg;
        private TextView ShipName, ShipActiveTxt, ShipShowDetailsName;
        private ImageView ShipActiveImg;
        private ImageButton shareShipsImtBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ShipImg = itemView.findViewById(R.id.ShipImg);
            ShipName = itemView.findViewById(R.id.ShipName);
            ShipActiveTxt = itemView.findViewById(R.id.ShipActiveTxt);
            ShipShowDetailsName = itemView.findViewById(R.id.ShipShowDetailsName);
            ShipActiveImg = itemView.findViewById(R.id.ShipActiveImg);
            shareShipsImtBtn = itemView.findViewById(R.id.shareShipsImtBtn);
        }
    }
}
