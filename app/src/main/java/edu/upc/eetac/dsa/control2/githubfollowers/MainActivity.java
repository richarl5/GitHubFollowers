package edu.upc.eetac.dsa.control2.githubfollowers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import edu.upc.eetac.dsa.control2.githubfollowers.entity.User;
import edu.upc.eetac.dsa.control2.githubfollowers.client.ClientRetrofit;
import edu.upc.eetac.dsa.control2.githubfollowers.client.GlobalData;
import edu.upc.eetac.dsa.control2.githubfollowers.client.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private String name;
    public static GlobalData globalData;
    private ProgressBar progressBar;


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
        globalData = (GlobalData)this.getApplication();
    }


    public void getUser(){
        username.setError(null);
        name = username.getText().toString();
        ClientRetrofit clientRetrofit = RetroClient.getClientRetrofit();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Call<User> getUserCall = clientRetrofit.getGITUser(name);
        getUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                GlobalData globalData = MainActivity.this.globalData;
                globalData.setUser(user);
                Intent intent = new Intent(MainActivity.this,Perfil.class);
                progressBar.setVisibility(View.GONE);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "User not found!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
