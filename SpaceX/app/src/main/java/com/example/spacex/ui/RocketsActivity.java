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
                adapter = new RocketsAdapter(RocketsActivity.this,rocketsModels);
                RocketsRecyclerView.setAdapter(adapter);

                RocketsRecyclerView.setVisibility(View.VISIBLE);
                RocketsProgress.setVisibility(View.GONE);
            }
        });

    }

}