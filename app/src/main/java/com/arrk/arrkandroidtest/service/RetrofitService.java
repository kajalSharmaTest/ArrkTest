package com.arrk.arrkandroidtest.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * This class represents the people service.
 *
 *
 * @author Kajal Sharma
 * @date 19/05/2018.
 */
public class RetrofitService {
    private static Retrofit retrofit = null;


    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    public PeopleAPI getAPI() {
        return getRetrofitInstance().create(PeopleAPI.class);
    }

    /**
     * This method return retrofit instance.
     *
     */
    public static Retrofit getRetrofitInstance(){
        if(retrofit == null) {
                retrofit = new Retrofit
                        .Builder()
                        .baseUrl(PeopleAPI.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }
        return retrofit;
    }
}
