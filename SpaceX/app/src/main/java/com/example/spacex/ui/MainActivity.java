package com.example.spacex.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.spacex.R;
import com.example.spacex.database.AppDataBase;
import com.example.spacex.databinding.ActivityMainBinding;
import com.example.spacex.model.RocketsModel;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ActivityMainBinding binding;
    private SharedPreferences preferences;
    private ActionBarDrawerToggle mToggle;
    private RocketsViewModel rocketsViewModel;
    private String UserEmail, UserName;
    private TextView UserNameTxt;
    private AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences("UserFile", Context.MODE_PRIVATE);
        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"First")
                .allowMainThreadQueries()
                .build();

        binding.MainNavigation.setNavigationItemSelectedListener(this);
        mToggle=new ActionBarDrawerToggle(this,binding.MainDrawer,R.string.open,R.string.close);
        binding.MainDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //retrieve rockets data from API
        rocketsViewModel = ViewModelProviders.of(this).get(RocketsViewModel.class);
        rocketsViewModel.getRockets();
        rocketsViewModel.RocketsMutableLiveData.observe(this, new Observer<List<RocketsModel>>() {
            @Override
            public void onChanged(List<RocketsModel> rocketsModels) {
                Picasso.get().load(rocketsModels.get(0).getFlickr_images().get(0)).into(binding.MainRocket1Img);
                binding.MainRocket1Name.setText(rocketsModels.get(0).getRocket_name());
                binding.MainRocket1Type.setText("Engine Type: "+rocketsModels.get(0).getEngines().getType());

                Picasso.get().load(rocketsModels.get(1).getFlickr_images().get(0)).into(binding.MainRocket2Img);
                binding.MainRocket2Name.setText(rocketsModels.get(1).getRocket_name());
                binding.MainRocket2Type.setText("Engine Type: "+rocketsModels.get(1).getEngines().getType());

            }
        });


        //on click to any button
        ButtonsOnClicked();

    }

    @Override
    protected void onStart() {
        super.onStart();
        DefineUserNameInDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id == R.id.CompanyId){
           Intent intent = new Intent(MainActivity.this,CompanyActivity.class);
           startActivity(intent);
        }
        else if(id == R.id.RocketsID){
            Intent intent = new Intent(MainActivity.this,RocketsActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.LaunchesID){
            Intent intent = new Intent(MainActivity.this,LaunchesActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.DragonsID){
            Intent intent = new Intent(MainActivity.this,DragonsActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.ShipsID){
            Intent intent = new Intent(MainActivity.this,ShipsActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.LogoutID){
            CheckLogout();
        }
        binding.MainDrawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void DefineUserNameInDrawer(){
        UserEmail = preferences.getString("Email","");
        UserName = db.getPersonName(UserEmail);

        View view = binding.MainNavigation.getHeaderView(0);
        UserNameTxt = view.findViewById(R.id.HeaderName);
        UserNameTxt.setText(UserName);
    }

    private void CheckLogout(){
        AlertDialog.Builder checkAlert = new AlertDialog.Builder(MainActivity.this);
        checkAlert.setMessage("Do you want to Logout?")
                .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.putString("Email", "Failed");
                editor.apply();

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = checkAlert.create();
        alert.setTitle("Logout");
        alert.show();
    }


    private void ButtonsOnClicked(){

        binding.MainDragonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DragonsActivity.class);
                startActivity(intent);
            }
        });

        binding.MainViewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RocketsActivity.class);
                startActivity(intent);
            }
        });

        binding.MainDiscoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Earth%27s_orbit"));
                startActivity(intent);
            }
        });

    }
}

