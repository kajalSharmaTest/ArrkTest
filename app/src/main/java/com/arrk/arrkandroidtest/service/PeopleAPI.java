package com.arrk.arrkandroidtest.service;


import com.arrk.arrkandroidtest.model.RestResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This class represents the Countries API, all endpoints can stay here.
 *
 *
 * @author Kajal Sharma
 * @date 19/05/2018.
 */
public interface PeopleAPI {
    String BASE_URL = "https://swapi.co/";

    @GET("api/people/?page=")
    Call<RestResponse> getPeopleData(@Query("page") String page);

}
