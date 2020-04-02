package com.example.weatherapp.common.view;

import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.weatherapp.fabric.NoConnectionDialogFabric;
import com.example.weatherapp.service.ConnectivityReceiver;

public abstract class WeatherAppActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    protected abstract ViewGroup getCoordinatorContainerView();

    private INoConnectionDialog noConnectionSnackBarDialog;

    @Override
    protected void onStart() {
        super.onStart();
        setupNoConnectionBar();
        ConnectivityReceiver.addConnectivityReceiverListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ConnectivityReceiver.removeConnectivityReceiverListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(Boolean isConnected) {
        if (isConnected) {
            noConnectionSnackBarDialog.hide();
        } else {
            noConnectionSnackBarDialog.show();
        }
    }

    private void setupNoConnectionBar() {
        ViewGroup coordinatorView = getCoordinatorContainerView();

        if (!(coordinatorView instanceof CoordinatorLayout)) {
            throw new IllegalArgumentException("CoordinatorLayout is required");
        }

        noConnectionSnackBarDialog = NoConnectionDialogFabric.get(coordinatorView);
    }
}
