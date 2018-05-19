package com.arrk.arrkandroidtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arrk.arrkandroidtest.model.Character;
import com.arrk.arrkandroidtest.presenter.CharacterDetailPresenter;
import com.arrk.arrkandroidtest.view.CharacterDetailView;
import com.google.gson.Gson;

/**
 * Created by Kajal on 19/05/2018.
 */

public class CharacterDetailActivity extends AppCompatActivity implements CharacterDetailView {
    private final String TAG = CharacterDetailActivity.class.getSimpleName();
    ProgressBar mSpinner ;

    CharacterDetailPresenter detailPresenter;
    TextView mName;
    TextView mHeight;
    TextView mMass;
    TextView mDateTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_detail);
         mName = (TextView)findViewById(R.id.tv_val_name);
         mHeight = (TextView)findViewById(R.id.tv_val_height);
         mMass = (TextView)findViewById(R.id.tv_val_mass);
         mDateTime = (TextView)findViewById(R.id.tv_val_record_time);

        Gson gson = new Gson();
        Character character = gson.fromJson(getIntent().getStringExtra("character"), Character.class);
        mSpinner = (ProgressBar)findViewById(R.id.detail_progress);
        detailPresenter = new CharacterDetailPresenter(this);
        detailPresenter.showSpinner(true);
       detailPresenter.displayDetails(character);
    }

    @Override
    public void detailReady(Character character) {
        detailPresenter.showSpinner(false);
        String CreatedDateTime = character.getCreated().substring(0,character.getCreated().length()-8);
        mName.setText(character.getName());
         mHeight.setText(character.getHeight()+" mt");
         mMass.setText(character.getMass()+" Kg");
         mDateTime.setText(CreatedDateTime.replace("T", " "));
    }

    @Override
    public void showSpinner(boolean state){
        if(state == true){
            mSpinner.setVisibility(View.VISIBLE);
        } else{
            mSpinner.setVisibility(View.GONE);
        }

    }
}
