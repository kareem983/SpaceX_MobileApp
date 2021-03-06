package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.spacex.adapter.LaunchesAdapter;
import com.example.spacex.databinding.ActivityLaunchesBinding;
import com.example.spacex.model.LaunchesModel;
import java.util.List;

public class LaunchesActivity extends AppCompatActivity {

    private ActivityLaunchesBinding binding;
    private LaunchesAdapter adapter;
    private LaunchesViewModel launchesViewModel;
    private LaunchesAdapter.RecyclerViewClickListener listener;
    private List<LaunchesModel> listOfLaunches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLaunchesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.launchesRecycler.setLayoutManager(new LinearLayoutManager(this));
        launchesViewModel = ViewModelProviders.of(this).get(LaunchesViewModel.class);
        launchesViewModel.getLaunches();
        launchesViewModel.LaunchesMutableLiveData.observe(this, new Observer<List<LaunchesModel>>() {
            @Override
            public void onChanged(List<LaunchesModel> launchesModels) {
                listOfLaunches = launchesModels;
                adapter = new LaunchesAdapter(LaunchesActivity.this, launchesModels, listener);
                binding.launchesRecycler.setAdapter(adapter);

                binding.launchesProgress.setVisibility(View.GONE);
                binding.launchesRecycler.setVisibility(View.VISIBLE);
            }
        });


        listener = new LaunchesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                LaunchesModel launchesModel = listOfLaunches.get(position);
                Intent intent = new Intent(LaunchesActivity.this, LaunchesInfoActivity.class);
                intent.putExtra("Launches Object", launchesModel);
                startActivity(intent);
            }
        };
    }
}