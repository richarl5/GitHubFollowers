package edu.upc.eetac.dsa.control2.githubfollowers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_DATA_NAME = "global_data";
    private EditText username;
    private String name;
    private List<Follower> followerList = new ArrayList<>();
    final GlobalData globalData = (GlobalData) getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUser();
            }
        });
    }


    public void getUser(){

        username.setError(null);
        name = username.getText().toString();
        ClientRetrofit clientRetrofit = RetroClient.getClientRetrofit();
        Call<User> getUserCall = clientRetrofit.getGITUser(name);
        getUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                globalData.setUser(user);
                getFollowers();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Toast.makeText(MainActivity.this, "User not found!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getFollowers(){
        ClientRetrofit clientRetrofit = RetroClient.getClientRetrofit();
        Call<List<Follower>> getFollowersCall = clientRetrofit.getGITFollowers(name);
        getFollowersCall.enqueue(new Callback<List<Follower>>() {
            @Override
            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                followerList = response.body();
                globalData.setFollowerList(followerList);
                Intent intent = new Intent(MainActivity.this, Perfil.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Follower>> call, Throwable t) {
                call.cancel();
                Toast.makeText(MainActivity.this, "Error getting followers", Toast.LENGTH_LONG).show();
            }
        });


    }


}
