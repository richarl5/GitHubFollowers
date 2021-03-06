package edu.upc.eetac.dsa.control2.githubfollowers.client;

import java.util.List;
import edu.upc.eetac.dsa.control2.githubfollowers.entity.Follower;
import edu.upc.eetac.dsa.control2.githubfollowers.entity.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ClientRetrofit {

    @GET("users/{username}")
    Call<User> getGITUser(@Path("username") String username);

    @GET("users/{username}/followers")
    Call<List<Follower>> getGITFollowers(@Path("username") String username);

}
