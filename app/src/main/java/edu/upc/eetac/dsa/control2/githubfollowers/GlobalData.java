package edu.upc.eetac.dsa.control2.githubfollowers;

import android.app.Application;
import android.content.res.Configuration;

import java.util.List;



/**
 * Created by pauli on 12/05/2017.
 */

public class GlobalData extends Application {

    private User user;
    private List<Follower> followerList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Follower> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<Follower> followerList) {
        this.followerList = followerList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
