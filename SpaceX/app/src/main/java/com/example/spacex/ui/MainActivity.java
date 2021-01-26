package com.example.spacex.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.spacex.R;
import com.example.spacex.database.AppDataBase;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SharedPreferences preferences;
    private ActionBarDrawerToggle mToggle;
    private String UserEmail, UserName;
    private TextView UserNameTxt;
    private AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"First")
                .allowMainThreadQueries()
                .build();
        preferences = getSharedPreferences("UserFile", Context.MODE_PRIVATE);

        drawerLayout = findViewById(R.id.Drawer);
        navigationView = findViewById(R.id.Navigation);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void DefineUserNameInDrawer(){
        UserEmail = preferences.getString("Email","");
        UserName = db.getPersonName(UserEmail);

        View view = navigationView.getHeaderView(0);
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
}

