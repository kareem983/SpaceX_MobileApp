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
import com.example.spacex.adapter.DragonsAdapter;
import com.example.spacex.model.DragonsModel;
import java.util.List;

public class DragonsActivity extends AppCompatActivity {

    private RecyclerView dragonsRecycler;
    private ProgressBar dragonsProgress;
    private DragonsAdapter adapter;
    private DragonsViewModel dragonsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragons);


        dragonsRecycler = findViewById(R.id.dragonsRecycler);
        dragonsProgress = findViewById(R.id.DragonsProgress);
        dragonsRecycler.setLayoutManager(new LinearLayoutManager(this));

        dragonsViewModel = ViewModelProviders.of(this).get(DragonsViewModel.class);
        dragonsViewModel.getDragons();
        dragonsViewModel.DragonsMutableLiveData.observe(this, new Observer<List<DragonsModel>>() {
            @Override
            public void onChanged(List<DragonsModel> dragonsModels) {
                adapter = new DragonsAdapter(DragonsActivity.this, dragonsModels);
                dragonsRecycler.setAdapter(adapter);

                dragonsProgress.setVisibility(View.GONE);
                dragonsRecycler.setVisibility(View.VISIBLE);
            }
        });
    }
}