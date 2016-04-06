package edu.uwi.sta.comp3275assignment2;

import android.Manifest;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;

import models.DBHelper;
import models.GPSDataContract;

public class LocationService extends Service implements LocationListener {
    LocationManager locationManager;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return -1;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
        return Service.START_STICKY;
    }


    @Override
    public void onLocationChanged(Location location) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(this);
        storeCoordinates(location);
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void storeCoordinates(final Location location) {
        final Context context = this;
        Thread thd = new Thread(new Runnable() {
            @Override
            public void run() {
                final SQLiteDatabase db = (new DBHelper(context)).getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(GPSDataContract.GPSDataEntry.LATITUDE, location.getLatitude());
                cv.put(GPSDataContract.GPSDataEntry.LONGITUDE, location.getLongitude());
                cv.put(GPSDataContract.GPSDataEntry.ALTITUDE, location.getAltitude());

                db.insert(GPSDataContract.GPSDataEntry.TABLE_NAME, null, cv);
            }
        });
        thd.start();
    }
}
