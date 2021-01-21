package com.example.spacex;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {
    private ImageView RocketImg,NameImg;

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
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 3500);
    }
}