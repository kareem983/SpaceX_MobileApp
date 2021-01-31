package com.example.spacex.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.spacex.R;
import com.example.spacex.model.ShipsModel;
import com.squareup.picasso.Picasso;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShipsAdapter extends RecyclerView.Adapter<ShipsAdapter.ViewHolder> {
    private RecyclerViewClickListener listener;
    private List<ShipsModel> ShipsModelsArrayList;
    private Context context;

    public ShipsAdapter(Context context, List<ShipsModel> ShipsModelsArrayList, RecyclerViewClickListener listener) {
        this.context = context;
        this.ShipsModelsArrayList = ShipsModelsArrayList;
        this.listener = listener;
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
        final ShipsModel shipsModel = ShipsModelsArrayList.get(position);
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
            holder.ShipActiveTxt.setText("Inactive");
            holder.ShipActiveTxt.setTextColor(Color.RED);
        }

        holder.shareShipsImtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weight, year_built, url;
                if(shipsModel.getWeight_kg()!=0)
                    weight = shipsModel.getWeight_kg()+ " Kg";
                else weight = "not available";
                if(shipsModel.getYear_built() !=0)
                    year_built = shipsModel.getYear_built()+"";
                else year_built = "not available";

                if(shipsModel.getUrl() !=null) {
                    url = shipsModel.getUrl();
                }
                else url = "URL not available";

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Ship Name: "+shipsModel.getShip_name()
                        +"\nShip weight is "+weight+"\nShip year built: "+year_built
                        +"\nShip type is "+shipsModel.getShip_type()+"\nnumber of missions is "+shipsModel.getMissions().size()
                        +"\nShip website Link: "+url);

                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);
            }
        });

        holder.Ship_item_Container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RelativeLayout Ship_item_Container;
        private CircleImageView ShipImg;
        private TextView ShipName, ShipActiveTxt;
        private ImageView ShipActiveImg;
        private ImageButton shareShipsImtBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Ship_item_Container = itemView.findViewById(R.id.Ship_item_Container);
            ShipImg = itemView.findViewById(R.id.ShipImg);
            ShipName = itemView.findViewById(R.id.ShipName);
            ShipActiveTxt = itemView.findViewById(R.id.ShipActiveTxt);
            ShipActiveImg = itemView.findViewById(R.id.ShipActiveImg);
            shareShipsImtBtn = itemView.findViewById(R.id.shareShipsImtBtn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

}
