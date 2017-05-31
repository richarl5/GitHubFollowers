package edu.upc.eetac.dsa.control2.githubfollowers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pauli on 15/05/2017.
 */

public class RetroClient {

    public static final String BASE_URL = "https://api.github.com/";


    //Get Retrofit Instance
    private static Retrofit getRetrofitInstance() {

            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    }
    // Create an instance of our Etackemon API interface.
    //clientRetrofit = retrofit.create(ClientRetrofit.class);

    //Get API service
    public static ClientRetrofit getClientRetrofit(){


        return getRetrofitInstance().create(ClientRetrofit.class);
    }


}
