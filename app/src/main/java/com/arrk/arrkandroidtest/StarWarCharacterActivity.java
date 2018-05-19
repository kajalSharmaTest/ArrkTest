package com.arrk.arrkandroidtest;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.arrk.arrkandroidtest.model.Character;
import com.arrk.arrkandroidtest.presenter.CharacterPresenter;
import com.arrk.arrkandroidtest.utils.NetworkConnectivity;
import com.arrk.arrkandroidtest.utils.PaginationAdapter;
import com.arrk.arrkandroidtest.utils.PaginationScrollListener;
import com.arrk.arrkandroidtest.view.CharacterView;

import java.util.List;

public class StarWarCharacterActivity extends AppCompatActivity implements CharacterView {
    private final String TAG = StarWarCharacterActivity.class.getSimpleName();
    ProgressBar mSpinner ;
    PaginationAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rv;
    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 8;
    private int currentPage = PAGE_START;
    CharacterPresenter characterPresenter;
    NetworkConnectivity connectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_war_character);
        mSpinner = (ProgressBar)findViewById(R.id.main_progress);
        rv = (RecyclerView) findViewById(R.id.main_recycler);
         characterPresenter = new CharacterPresenter(this);
        characterPresenter.showSpinner(true);
        adapter = new PaginationAdapter(this);
        connectivity = new NetworkConnectivity(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);

        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadPeopleData();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


        // mocking network delay for API call
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadPeopleData();
            }
        }, 1000);
    }


    private void loadPeopleData() {
        Log.d(TAG, "loadNextPage: " + currentPage);
        try {
            if (connectivity != null && connectivity.isConnected()) {
                characterPresenter.getPeopleList(currentPage + 1);
            } else {
                showNetworkAlert();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void peoplelistReady(List<Character> countries) {
        List<Character> peopleList = countries;
        characterPresenter.showSpinner(false);
        if(currentPage>0){
            adapter.removeLoadingFooter();
            isLoading = false;
        }
        adapter.addAll(peopleList);

        if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
        else isLastPage = true;
    }

    @Override
    public void showSpinner(boolean state){
        if(state == true){
            mSpinner.setVisibility(View.VISIBLE);
        } else{
            mSpinner.setVisibility(View.GONE);
        }
    }

    private void showNetworkAlert(){
            AlertDialog.Builder builder =new AlertDialog.Builder(this);
            builder.setTitle("No internet Connection");
            builder.setMessage("Please turn on internet connection to continue");
            builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   loadPeopleData();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
    }
}
