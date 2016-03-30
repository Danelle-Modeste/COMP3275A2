package edu.uwi.sta.comp3275assignment2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;

public class SimpleLocationListener implements LocationListener {
    GPS GPSActivity;

    public SimpleLocationListener(GPS GPSActivity) {
        this.GPSActivity = GPSActivity;
    }


    @Override
    public void onLocationChanged(Location location) {
        String latitude =String.valueOf(location.getLatitude());
        String longitude =String.valueOf(location.getLongitude());
        String altitude =String.valueOf(location.getAltitude());
        GPSActivity.changeLocation(latitude,longitude,altitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
