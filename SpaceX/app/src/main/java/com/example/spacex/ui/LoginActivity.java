package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.spacex.database.AppDataBase;
import com.example.spacex.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private AppDataBase db;
    public static Activity fa;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fa = this;

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,"First")
                .allowMainThreadQueries()
                .build();


        binding.LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckExistence();
            }
        });

        binding.goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    private void CheckExistence(){
        String EmailValue = binding.LoginEmailEdit.getText().toString().trim(), PasswordValue = binding.LoginPasswordEdit.getText().toString();

        if(EmailValue.isEmpty() ||PasswordValue.isEmpty())
            Toast.makeText(LoginActivity.this,"Empty cell", Toast.LENGTH_SHORT).show();

        else {
            String EmailIsExist = db.getPersonEmail(EmailValue, PasswordValue);
            if (EmailIsExist != null) {
                preferences = getSharedPreferences("UserFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Email", EmailValue);
                editor.apply();

                Toast.makeText(LoginActivity.this, "welcome back", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(LoginActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
        }
    }

}