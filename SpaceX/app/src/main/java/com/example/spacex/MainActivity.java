package com.example.spacex;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.tx);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiInterface = retrofit.create(ApiInterface.class);
        Call<LaunchesModel> post = apiInterface.getPost();
        post.enqueue(new Callback<LaunchesModel>() {
            @Override
            public void onResponse(Call<LaunchesModel> call, Response<LaunchesModel> response) {
               Picasso.get().load(response.body().getLinks().getMission_patch_small()).into(imageView);
            }
            @Override
            public void onFailure(Call<LaunchesModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}