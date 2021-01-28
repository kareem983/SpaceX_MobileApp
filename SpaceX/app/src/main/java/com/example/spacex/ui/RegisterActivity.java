package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.spacex.database.AppDataBase;
import com.example.spacex.database.UserEntityDatabase;
import com.example.spacex.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private AppDataBase db;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"First")
                .allowMainThreadQueries()
                .build();

        binding.RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertPersonInDataBase();
            }
        });

        binding.goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void InsertPersonInDataBase(){
        String NameValue = binding.RegisterNameEdit.getText().toString().trim(), EmailValue = binding.RegisterEmailEdit.getText().toString().trim(),
                PasswordValue = binding.RegisterPasswordEdit.getText().toString(), CPasswordValue = binding.RegisterCPasswordEdit.getText().toString();

        if(AllCheck(NameValue, EmailValue, PasswordValue, CPasswordValue)){
            long IsInserted = db.InsertPerson(new UserEntityDatabase(NameValue, EmailValue, PasswordValue));

            if(IsInserted==-1)
                Toast.makeText(RegisterActivity.this,"You already have an account", Toast.LENGTH_SHORT).show();

            else{
             //shared pre ************

                preferences = getSharedPreferences("UserFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Email", EmailValue);
                editor.apply();

                Toast.makeText(RegisterActivity.this,"You registered successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                LoginActivity.fa.finish();
                finish();
            }
        }

    }

    private boolean AllCheck(String Name, String Email, String Password, String CPassword){
        boolean IsOk =false;
        if(Name.isEmpty() ||Email.isEmpty() || Password.isEmpty() || CPassword.isEmpty())
            Toast.makeText(RegisterActivity.this,"Empty cell", Toast.LENGTH_SHORT).show();

        else {
            if (!CheckConfirmPassword(Password, CPassword)) {
                Toast.makeText(RegisterActivity.this,"Password Should be identical", Toast.LENGTH_SHORT).show();
                binding.RegisterCPasswordEdit.setText("");
            }
            else IsOk =true;
        }
        return IsOk;
    }

    private boolean CheckConfirmPassword(String Password, String CPassword){
        return Password.equals(CPassword);
    }

}