package edu.uwi.sta.comp3275assignment2;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.DBHelper;
import models.GPSDataContract;

public class AllStoredLocations extends AppCompatActivity {
    ListView GPSdata;
    ArrayList<Map> coordinates;
    ArrayAdapter adapter;
    public final int LOCATION_REQUEST_CODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stored_locations);
        requestLocationPermissions();
        setUpUi();
        loadData();
        setUpAdapter();


    }
    protected void setUpUi(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GPSdata = (ListView)findViewById(R.id.gps_list);
        coordinates= new ArrayList<>();
    }

    protected void setUpAdapter(){
        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,coordinates);
        GPSdata.setAdapter(adapter);
    }

    protected  void loadData(){
        final Context context = this;
        Thread thd = new Thread(new Runnable() {
            @Override
            public void run() {
                final SQLiteDatabase db = (new DBHelper(context)).getReadableDatabase();
                    String[] fields = new String[]{GPSDataContract.GPSDataEntry.LATITUDE,GPSDataContract.GPSDataEntry.LONGITUDE,
                            GPSDataContract.GPSDataEntry.ALTITUDE,GPSDataContract.GPSDataEntry.TIME
                    };

                Cursor i= db.query(GPSDataContract.GPSDataEntry.TABLE_NAME,fields,null,null,null,null,GPSDataContract.GPSDataEntry.TIME+ " DESC");
                while(i.moveToNext()){
                    Double Latitude= i.getDouble(i.getColumnIndex(GPSDataContract.GPSDataEntry.LATITUDE));
                    Double Longitude = i.getDouble(i.getColumnIndex(GPSDataContract.GPSDataEntry.LONGITUDE));
                    Double Altitude= i.getDouble(i.getColumnIndex(GPSDataContract.GPSDataEntry.ALTITUDE));
                    String Time= i.getString(i.getColumnIndex(GPSDataContract.GPSDataEntry.TIME));

                    Map map = new HashMap();
                    map.put("latitude",Latitude);
                    map.put("longitude",Longitude);
                    map.put("altitude",Altitude);
                    map.put("time updated",Time);
                    coordinates.add(map);
                }
                GPSdata.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        thd.start();
    }

    public void requestLocationPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        }
    }

}