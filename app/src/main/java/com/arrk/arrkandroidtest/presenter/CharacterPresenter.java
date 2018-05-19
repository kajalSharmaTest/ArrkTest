package com.arrk.arrkandroidtest.presenter;



import com.arrk.arrkandroidtest.model.Character;
import com.arrk.arrkandroidtest.model.RestResponse;
import com.arrk.arrkandroidtest.service.RetrofitService;
import com.arrk.arrkandroidtest.view.CharacterView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * This class represents the people view interface.
 *
 *
 * @author Kajal Sharma
 * @date 19/05/2018.
 */
public class CharacterPresenter {

    private CharacterView characterView;
    private RetrofitService retrofitService;

    public CharacterPresenter(CharacterView view) {
        this.characterView = view;

        if (this.retrofitService == null) {
            this.retrofitService = new RetrofitService();
        }
    }

    public void showSpinner(boolean state){
        characterView.showSpinner(state);
    }

    public void getPeopleList(int page) {
        retrofitService
                .getAPI()
                .getPeopleData(String.valueOf(page))
                .enqueue(new Callback<RestResponse>() {
                    @Override
                    public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
                        RestResponse data = response.body();

                        if (data != null && data != null) {
                            List<Character> result = data.getResults();
                            characterView.peoplelistReady(result);
                        }
                    }

                    @Override
                    public void onFailure(Call<RestResponse> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
