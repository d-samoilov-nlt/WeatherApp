package com.example.weatherapp.fabric;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.weatherapp.R;
import com.example.weatherapp.view.common.INoConnectionDialog;
import com.example.weatherapp.view.common.NoConnectionSnackBarDialog;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE;

public class NoConnectionDialogFabric {

    public static INoConnectionDialog get(@NonNull ViewGroup parent){
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View content = inflater.inflate(R.layout.no_connection_snack_bar, parent, false);
        final NoConnectionSnackBarDialog.ContentViewCallback viewCallback = new NoConnectionSnackBarDialog.ContentViewCallback(content);
        final NoConnectionSnackBarDialog customSnackBar = new NoConnectionSnackBarDialog(parent, content, viewCallback);

        customSnackBar.getView().setPadding(0, 0, 0, 0);
        customSnackBar.setDuration(LENGTH_INDEFINITE);

        return customSnackBar;
    }
}
