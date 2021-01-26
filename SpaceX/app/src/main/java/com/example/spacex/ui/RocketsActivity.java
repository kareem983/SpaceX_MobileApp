package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import com.example.spacex.R;
import com.example.spacex.adapter.RocketsAdapter;
import com.example.spacex.model.RocketsModel;
import java.util.List;

public class RocketsActivity extends AppCompatActivity {

    private RocketsViewModel rocketsViewModel;
    private RecyclerView RocketsRecyclerView;
    private RocketsAdapter adapter;
    private ProgressBar RocketsProgress;
    private RocketsAdapter.RecyclerViewClickListener listener;
    private List<RocketsModel> listOfRocketsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rockets);

        RocketsRecyclerView = findViewById(R.id.rocketsRecycler);
        RocketsProgress = findViewById(R.id.RocketsProgress);
        RocketsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        rocketsViewModel = ViewModelProviders.of(this).get(RocketsViewModel.class);
        rocketsViewModel.getRockets();
        rocketsViewModel.RocketsMutableLiveData.observe(this, new Observer<List<RocketsModel>>() {
            @Override
            public void onChanged(List<RocketsModel> rocketsModels) {
                listOfRocketsModel =rocketsModels;
                adapter = new RocketsAdapter(RocketsActivity.this,rocketsModels, listener);
                RocketsRecyclerView.setAdapter(adapter);

                RocketsRecyclerView.setVisibility(View.VISIBLE);
                RocketsProgress.setVisibility(View.GONE);
            }
        });


        listener = new RocketsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                RocketsModel rocketsModel = listOfRocketsModel.get(position);
                //go to RocketInfo Activity
            }
        };

    }
}