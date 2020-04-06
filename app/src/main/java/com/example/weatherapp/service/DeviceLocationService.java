package com.example.weatherapp.service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import androidx.core.app.ActivityCompat;

import com.example.weatherapp.data.deviceLocation.DeviceLocation;
import com.example.weatherapp.data.deviceLocation.IDeviceLocation;
import com.example.weatherapp.domain.exception.CantGetDeviceLocationException;
import com.example.weatherapp.domain.exception.PermissionDeniedException;

public class DeviceLocationService extends Service implements LocationListener {

    private OnLocationUpdateListener onLocationUpdateListener;
    private IBinder binder;
    private IDeviceLocation lastDeviseLocation;
    private LocationManager locationManager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        binder = new LocalBinder();
        requestLocationData();

        return START_NOT_STICKY;
    }

    private void requestLocationData() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED

                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw new PermissionDeniedException("Location permission denied");
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager == null) {
            throw new CantGetDeviceLocationException();
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }


    public void attachListener(OnLocationUpdateListener onLocationUpdateListener) {
        this.onLocationUpdateListener = onLocationUpdateListener;
    }

    public void removeListener() {
        this.onLocationUpdateListener = null;
        this.locationManager.removeUpdates(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        public DeviceLocationService getServiceInstance() {
            return DeviceLocationService.this;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (onLocationUpdateListener == null) {
            return;
        }

        IDeviceLocation currentDeviceLocation =
                new DeviceLocation(
                        location.getLongitude(),
                        location.getLatitude());

        if (lastDeviseLocation == null) {
            lastDeviseLocation = currentDeviceLocation;
            onLocationUpdateListener.onUpdated(lastDeviseLocation);

        } else if (!lastDeviseLocation.equals(currentDeviceLocation)) {
            lastDeviseLocation = currentDeviceLocation;
            onLocationUpdateListener.onUpdated(lastDeviseLocation);
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // nop
    }

    @Override
    public void onProviderEnabled(String provider) {
        // nop
    }

    @Override
    public void onProviderDisabled(String provider) {
        // nop
    }

    public interface OnLocationUpdateListener extends ServiceConnection {
        void onUpdated(IDeviceLocation deviceLocation);
    }
}
