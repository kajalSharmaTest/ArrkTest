package com.arrk.arrkandroidtest;

import com.arrk.arrkandroidtest.model.RestResponse;
import com.arrk.arrkandroidtest.service.PeopleAPI;
import com.arrk.arrkandroidtest.service.RetrofitService;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Kajal
 */

public class ApiCallTest {

        @Test
        public void api_call_success() {

            PeopleAPI apiEndpoints = RetrofitService.getRetrofitInstance().create(PeopleAPI.class);

            Call<RestResponse> call = apiEndpoints.getPeopleData(String.valueOf(1));

            try {
                //Magic is here at .execute() instead of .enqueue()
                Response<RestResponse> response = call.execute();
                RestResponse authResponse = response.body();

                assertTrue(response.isSuccessful() && authResponse.getCount() == 87);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }