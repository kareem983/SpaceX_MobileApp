package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
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
import com.example.spacex.data.UserEntityDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText NameEdit, EmailEdit, PasswordEdit, CPasswordEdit;
    private Button RegisterBtn;
    private TextView GoToLogin;
    private AppDataBase db;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("");
        NameEdit = (TextInputEditText)findViewById(R.id.RNameEdit);
        EmailEdit = (TextInputEditText)findViewById(R.id.REmailEdit);
        PasswordEdit = (TextInputEditText)findViewById(R.id.RPasswordEdit);
        CPasswordEdit = (TextInputEditText)findViewById(R.id.RCPasswordEdit);
        RegisterBtn = findViewById(R.id.RegisterBtn);
        GoToLogin = findViewById(R.id.goToLogin);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"First")
                .allowMainThreadQueries()
                .build();

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertPersonInDataBase();
            }
        });

        GoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void InsertPersonInDataBase(){
        String NameValue = NameEdit.getText().toString().trim(), EmailValue = EmailEdit.getText().toString().trim(),
                PasswordValue = PasswordEdit.getText().toString(), CPasswordValue = CPasswordEdit.getText().toString();

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
                CPasswordEdit.setText("");
            }
            else IsOk =true;
        }
        return IsOk;
    }

    private boolean CheckConfirmPassword(String Password, String CPassword){
        return Password.equals(CPassword);
    }

}