package com.arrk.arrkandroidtest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Kajal on 19/05/2018.
 */

public class NetworkConnectivity {

    Context mContext ;

    public NetworkConnectivity(Context context) {
        mContext = context;
    }

    public  boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }
}
