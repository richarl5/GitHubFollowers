package edu.upc.eetac.dsa.control2.githubfollowers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import edu.upc.eetac.dsa.control2.githubfollowers.listView.List_adapter;
import edu.upc.eetac.dsa.control2.githubfollowers.listView.RowItem;
import edu.upc.eetac.dsa.control2.githubfollowers.entity.Follower;
import edu.upc.eetac.dsa.control2.githubfollowers.client.ClientRetrofit;
import edu.upc.eetac.dsa.control2.githubfollowers.client.GlobalData;
import edu.upc.eetac.dsa.control2.githubfollowers.client.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perfil extends AppCompatActivity {

    private List<Follower> followerList = new ArrayList<>();
    private ListView listView;
    private ProgressBar progressBar;
    List<RowItem> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        rowItems = new ArrayList<>();
        GlobalData globalData = (GlobalData)Perfil.this.getApplication();
        TextView repos = (TextView) findViewById(R.id.textView_nrepo);
        repos.setText(globalData.getUser().getPublic_repos());
        TextView followers = (TextView) findViewById(R.id.textView_nfollow);
        followers.setText(globalData.getUser().getFollowers());
        String follower = globalData.getUser().getFollowers();
        if(!follower.equals("0")) getFollowers();
        else progressBar.setVisibility(View.GONE);
    }

    public void getFollowers(){
        ClientRetrofit clientRetrofit = RetroClient.getClientRetrofit();
        GlobalData globalData= (GlobalData)Perfil.this.getApplication();
        String username= globalData.getLogin();
        Call<List<Follower>> getFollowersCall = clientRetrofit.getGITFollowers(username);
        getFollowersCall.enqueue(new Callback<List<Follower>>() {
            @Override
            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                followerList = response.body();
                progressBar.setVisibility(View.GONE);
                printList();
            }

            @Override
            public void onFailure(Call<List<Follower>> call, Throwable t) {
                call.cancel();
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Perfil.this, "Error getting followers", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void printList(){
        for (int i = 0; i < followerList.size(); i++){
            RowItem item = new RowItem(R.drawable.boy,followerList.get(i).getLogin());
            rowItems.add(item);
        }
        listView = (ListView) findViewById(R.id.list);
        List_adapter adapter = new List_adapter(this,R.layout.activity_entry,rowItems);
        listView.setAdapter(adapter);
    }

}
