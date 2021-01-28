package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.spacex.adapter.ShipsAdapter;
import com.example.spacex.databinding.ActivityShipsBinding;
import com.example.spacex.model.ShipsModel;
import java.util.List;

public class ShipsActivity extends AppCompatActivity {

    private ActivityShipsBinding binding;
    private ShipsAdapter adapter;
    private ShipsViewModel shipsViewModel;
    private ShipsAdapter.RecyclerViewClickListener listener;
    private List<ShipsModel> listOfShips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShipsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.shipsRecycler.setLayoutManager(new LinearLayoutManager(this));
        shipsViewModel = ViewModelProviders.of(this).get(ShipsViewModel.class);
        shipsViewModel.getShips();
        shipsViewModel.ShipsMutableLiveData.observe(this, new Observer<List<ShipsModel>>() {
            @Override
            public void onChanged(List<ShipsModel> shipsModels) {
                listOfShips = shipsModels;
                adapter = new ShipsAdapter(ShipsActivity.this,shipsModels, listener);
                binding.shipsRecycler.setAdapter(adapter);

                binding.shipsRecycler.setVisibility(View.VISIBLE);
                binding.ShipsProgress.setVisibility(View.GONE);
            }
        });

        listener = new ShipsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                ShipsModel shipsModel = listOfShips.get(position);
                Intent intent = new Intent(ShipsActivity.this, ShipsInfoActivity.class);
                intent.putExtra("Ships Object", shipsModel);
                startActivity(intent);
            }
        };
    }
}