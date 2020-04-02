package com.example.weatherapp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.weatherapp.util.UtilDeviceNetwork;

import java.util.ArrayList;
import java.util.List;

public class ConnectivityReceiver extends BroadcastReceiver {
    private static List<ConnectivityReceiverListener> connectivityReceiverListeners;

    @Override
    public void onReceive(Context context, Intent intent) {
        Boolean isConnected = UtilDeviceNetwork.isDeviceOnline(context.getApplicationContext());

        for (ConnectivityReceiverListener listener : getConnectivityReceiverListeners()) {
            listener.onNetworkConnectionChanged(isConnected);
        }
    }

    private static List<ConnectivityReceiverListener> getConnectivityReceiverListeners() {
        if (connectivityReceiverListeners == null) {
            connectivityReceiverListeners = new ArrayList<>();
        }

        return connectivityReceiverListeners;
    }

    public static void addConnectivityReceiverListener(ConnectivityReceiverListener listener) {
        getConnectivityReceiverListeners().add(listener);
    }

    public static void removeConnectivityReceiverListener(ConnectivityReceiverListener listener) {
        getConnectivityReceiverListeners().remove(listener);
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(Boolean isConnected);
    }
}
