package com.example.spacex.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.spacex.R;

public class SplashActivity extends AppCompatActivity {
    private ImageView RocketImg,NameImg;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        RocketImg = (ImageView)findViewById(R.id.RocketImg);
        NameImg = (ImageView)findViewById(R.id.NameImg);

        YoYo.with(Techniques.ZoomInDown)
                .duration(2000)
                .repeat(0)
                .playOn(RocketImg);

        YoYo.with(Techniques.FlipInX)
                .duration(3000)
                .repeat(0)
                .playOn(NameImg);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                preferences = getSharedPreferences("UserFile", Context.MODE_PRIVATE);
                String email = preferences.getString("Email","Failed");
                if(email.equals("Failed"))
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3500);
    }
}