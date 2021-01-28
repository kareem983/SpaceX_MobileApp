package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.spacex.adapter.RocketsAdapter;
import com.example.spacex.databinding.ActivityRocketsBinding;
import com.example.spacex.model.RocketsModel;
import java.util.List;

public class RocketsActivity extends AppCompatActivity {

    private ActivityRocketsBinding binding;
    private RocketsViewModel rocketsViewModel;
    private RocketsAdapter adapter;
    private RocketsAdapter.RecyclerViewClickListener listener;
    private List<RocketsModel> listOfRocketsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRocketsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rocketsRecycler.setLayoutManager(new LinearLayoutManager(this));

        rocketsViewModel = ViewModelProviders.of(this).get(RocketsViewModel.class);
        rocketsViewModel.getRockets();
        rocketsViewModel.RocketsMutableLiveData.observe(this, new Observer<List<RocketsModel>>() {
            @Override
            public void onChanged(List<RocketsModel> rocketsModels) {
                listOfRocketsModel =rocketsModels;
                adapter = new RocketsAdapter(RocketsActivity.this,rocketsModels, listener);
                binding.rocketsRecycler.setAdapter(adapter);

                binding.rocketsRecycler.setVisibility(View.VISIBLE);
                binding.RocketsProgress.setVisibility(View.GONE);
            }
        });


        listener = new RocketsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                RocketsModel rocketsModel = listOfRocketsModel.get(position);

                Intent intent = new Intent(RocketsActivity.this,RocketInfoActivity.class);
                intent.putExtra("Rocket Object", rocketsModel);
                startActivity(intent);
            }
        };

    }
}