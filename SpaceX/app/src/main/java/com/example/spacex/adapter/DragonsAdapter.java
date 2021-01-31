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
import com.example.spacex.model.DragonsModel;
import com.squareup.picasso.Picasso;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class DragonsAdapter extends RecyclerView.Adapter<DragonsAdapter.ViewHolder> {
    private RecyclerViewClickListener listener;
    private List<DragonsModel> DragonsModelsArrayList;
    private Context context;

    public DragonsAdapter(Context context, List<DragonsModel> DragonsModelsArrayList, RecyclerViewClickListener listener) {
        this.context = context;
        this.DragonsModelsArrayList = DragonsModelsArrayList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.dragon_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return DragonsModelsArrayList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DragonsModel dragonsModel = DragonsModelsArrayList.get(position);
        if(dragonsModel.getFlickr_images().get(0) !=null)
            Picasso.get().load(dragonsModel.getFlickr_images().get(0)).placeholder(R.drawable.rocket).into(holder.DragonImg);
        else holder.DragonImg.setImageResource(R.drawable.rocket);
        holder.DragonName.setText(dragonsModel.getName());

        if(dragonsModel.isActive()) {
            holder.DragonActiveImg.setImageResource(R.drawable.active);
            holder.DragonActiveTxt.setText("Active");
            holder.DragonActiveTxt.setTextColor(Color.GREEN);
        }
        else {
            holder.DragonActiveImg.setImageResource(R.drawable.inactive);
            holder.DragonActiveTxt.setText("Inactive");
            holder.DragonActiveTxt.setTextColor(Color.RED);
        }

        holder.shareDragonsImtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Dragon Name: "+dragonsModel.getName()
                        +"\nDragon weight is "+dragonsModel.getDry_mass_kg()+" Kg\nFirst Flight: "+dragonsModel.getFirst_flight()
                        +"\nDragon type is "+dragonsModel.getType()
                        +"\nWikipedia Link: "+dragonsModel.getWikipedia());

                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);
            }
        });

        holder.Dragon_item_Container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RelativeLayout Dragon_item_Container;
        private CircleImageView DragonImg;
        private TextView DragonName, DragonActiveTxt;
        private ImageView DragonActiveImg;
        private ImageButton shareDragonsImtBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Dragon_item_Container = itemView.findViewById(R.id.Dragon_item_Container);
            DragonImg = itemView.findViewById(R.id.DragonImg);
            DragonName = itemView.findViewById(R.id.DragonName);
            DragonActiveTxt = itemView.findViewById(R.id.DragonActiveTxt);
            DragonActiveImg = itemView.findViewById(R.id.DragonActiveImg);
            shareDragonsImtBtn = itemView.findViewById(R.id.shareDragonsImtBtn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }
}
