package edu.upc.eetac.dsa.control2.githubfollowers;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;



/**
 * Created by pauli on 02/05/2017.
 */

public interface ClientRetrofit {


    @GET("users/{username}")
    Call<User> getGITUser(@Path("username") String username);

    @GET("users/{username}/followers")
    Call<List<Follower>> getGITFollowers(@Path("username") String username);


}
