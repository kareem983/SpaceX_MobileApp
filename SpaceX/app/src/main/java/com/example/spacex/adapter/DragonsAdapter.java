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
import com.example.spacex.model.DragonsModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class DragonsAdapter extends RecyclerView.Adapter<DragonsAdapter.ViewHolder> {

    private List<DragonsModel> DragonsModelsArrayList = new ArrayList<>();
    private Context context;

    public DragonsAdapter(Context context, List<DragonsModel> DragonsModelsArrayList) {
        this.context = context;
        this.DragonsModelsArrayList = DragonsModelsArrayList;
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
        DragonsModel dragonsModel = DragonsModelsArrayList.get(position);
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
            holder.DragonActiveTxt.setText("inActive");
            holder.DragonActiveTxt.setTextColor(Color.RED);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView DragonImg;
        private TextView DragonName, DragonActiveTxt, DragonShowDetailsName;
        private ImageView DragonActiveImg;
        private ImageButton shareDragonsImtBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DragonImg = itemView.findViewById(R.id.DragonImg);
            DragonName = itemView.findViewById(R.id.DragonName);
            DragonActiveTxt = itemView.findViewById(R.id.DragonActiveTxt);
            DragonShowDetailsName = itemView.findViewById(R.id.DragonShowDetailsName);
            DragonActiveImg = itemView.findViewById(R.id.DragonActiveImg);
            shareDragonsImtBtn = itemView.findViewById(R.id.shareDragonsImtBtn);
        }
    }
}
