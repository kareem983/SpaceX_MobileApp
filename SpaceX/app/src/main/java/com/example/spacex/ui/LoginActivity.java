package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.spacex.R;
import com.example.spacex.data.AppDataBase;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText EmailEdit, PasswordEdit;
    private Button LoginBtn;
    private TextView GoToRegister;
    private AppDataBase db;
    public static Activity fa;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            setTitle("");
            fa = this;

            EmailEdit = (TextInputEditText)findViewById(R.id.EmailEdit);
            PasswordEdit = (TextInputEditText)findViewById(R.id.PasswordEdit);
            LoginBtn = findViewById(R.id.LoginBtn);
            GoToRegister = findViewById(R.id.goToRegister);

            db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,"First")
                    .allowMainThreadQueries()
                    .build();

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckExistence();
            }
        });


        GoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    private void CheckExistence(){
        String EmailValue = EmailEdit.getText().toString().trim(), PasswordValue = PasswordEdit.getText().toString();

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