package edu.upc.eetac.dsa.control2.githubfollowers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Perfil extends AppCompatActivity {

    private GlobalData globalData = null;
    private ListView listView;
    List<RowItem> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        globalData = (GlobalData) getApplication();
        User user = globalData.getUser();
        rowItems = new ArrayList<RowItem>();

        TextView repos = (TextView) findViewById(R.id.textView_nrepo);
        repos.setText(user.getPublic_repos());
        TextView followers = (TextView) findViewById(R.id.textView_nfollow);
        followers.setText(user.getFollowers());
        for (int i = 0; i < globalData.getFollowerList().size(); i++){
            RowItem item = new RowItem(R.drawable.boy,globalData.getFollowerList().get(i).getLogin());
            rowItems.add(item);
        }
        listView = (ListView) findViewById(R.id.list);
        List_adapter adapter = new List_adapter(this,R.layout.activity_entry,rowItems);
        listView.setAdapter(adapter);
    }
}
