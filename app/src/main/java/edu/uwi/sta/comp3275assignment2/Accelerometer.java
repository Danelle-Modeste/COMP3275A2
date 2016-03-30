package edu.uwi.sta.comp3275assignment2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Accelerometer extends AppCompatActivity {
    SensorManager sensorManager;
    SensorListener sensorListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        final Sensor accelSensor = sensorManager.getDefaultSensor((Sensor.TYPE_ACCELEROMETER));
        TextView disp_accel= (TextView)findViewById(R.id.txt_accel);

        sensorListener= new SensorListener(getApplicationContext(),disp_accel);
        sensorManager.registerListener(sensorListener,accelSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onDestroy(){
        super.onDestroy();
        sensorManager.unregisterListener(sensorListener);
    }
}
