package com.example.weatherapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class UtilDeviceNetwork {

    public static boolean isDeviceOnline(Context applicationContext) {
        ConnectivityManager connMgr = (ConnectivityManager)
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }
}
