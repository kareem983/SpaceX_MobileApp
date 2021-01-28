package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.spacex.adapter.DragonsAdapter;
import com.example.spacex.databinding.ActivityDragonsBinding;
import com.example.spacex.model.DragonsModel;
import java.util.List;

public class DragonsActivity extends AppCompatActivity {

    private ActivityDragonsBinding binding;
    private DragonsAdapter adapter;
    private DragonsViewModel dragonsViewModel;
    private DragonsAdapter.RecyclerViewClickListener listener;
    private List<DragonsModel> listOfDragons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDragonsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dragonsRecycler.setLayoutManager(new LinearLayoutManager(this));

        dragonsViewModel = ViewModelProviders.of(this).get(DragonsViewModel.class);
        dragonsViewModel.getDragons();
        dragonsViewModel.DragonsMutableLiveData.observe(this, new Observer<List<DragonsModel>>() {
            @Override
            public void onChanged(List<DragonsModel> dragonsModels) {
                listOfDragons = dragonsModels;
                adapter = new DragonsAdapter(DragonsActivity.this, dragonsModels, listener);
                binding.dragonsRecycler.setAdapter(adapter);

                binding.DragonsProgress.setVisibility(View.GONE);
                binding.dragonsRecycler.setVisibility(View.VISIBLE);
            }
        });

        listener = new DragonsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                DragonsModel dragonsModel = listOfDragons.get(position);
                Intent intent = new Intent(DragonsActivity.this, DragonInfoActivity.class);
                intent.putExtra("Dragon Object",dragonsModel);
                startActivity(intent);
            }
        };
    }
}