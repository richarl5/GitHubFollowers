package edu.upc.eetac.dsa.control2.githubfollowers;

import java.io.FileOutputStream;

/**
 * Created by Home on 31/05/2017.
 */

public class Follower {

    private String login;
    private String avatar_url;

    private Follower(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
