package com.arrk.arrkandroidtest.presenter;

import com.arrk.arrkandroidtest.model.Character;
import com.arrk.arrkandroidtest.model.RestResponse;
import com.arrk.arrkandroidtest.service.RetrofitService;
import com.arrk.arrkandroidtest.view.CharacterDetailView;
import com.arrk.arrkandroidtest.view.CharacterView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kajal on 19/05/2018.
 */

public class CharacterDetailPresenter {
    private CharacterDetailView characterDetailView;

    public CharacterDetailPresenter(CharacterDetailView view) {
        this.characterDetailView = view;
    }

    public void showSpinner(boolean state){
        characterDetailView.showSpinner(state);
    }

    public void displayDetails(Character character) {

        characterDetailView.detailReady(character);
    }

    }
