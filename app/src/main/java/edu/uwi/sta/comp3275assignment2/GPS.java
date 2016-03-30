package edu.uwi.sta.comp3275assignment2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GPS extends AppCompatActivity {
    private TextView latitude,longitude,altitude;
    LocationManager locationManager;
    LocationListener locationListener;
    public final int LOCATION_REQUEST_CODE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        latitude = (TextView) findViewById(R.id.txt_lat);
        longitude = (TextView) findViewById(R.id.txt_long);
        altitude = (TextView) findViewById(R.id.txt_alt);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new SimpleLocationListener(this);
        getLocation();
    }

    public void getLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    getLocation();
                }
                else {
                    Toast.makeText(getApplicationContext(),"GPS LOCATION ACCESS PERMISSIONS MUST BE GRANTED FOR THIS TO WORK",Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


    public void changeLocation(String lat, String longt, String alt) {
        latitude.setText(lat);
        longitude.setText(longt);
        altitude.setText(alt);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        else {
            locationManager.removeUpdates(locationListener);
        }
    }
}