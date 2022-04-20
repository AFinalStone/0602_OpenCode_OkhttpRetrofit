package com.afs.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.afs.retrofit.api.GitHubService;
import com.afs.retrofit.bean.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity========";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                GitHubService service = retrofit.create(GitHubService.class);
                Call<List<UserInfo>> repos = service.listRepos("AFinalStone");
                repos.enqueue(new Callback<List<UserInfo>>() {

                    @Override
                    public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                        List<UserInfo> list = response.body();
                        for (UserInfo user : list) {
                            Log.d(TAG, user.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                    }
                });
            }
        });
        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}