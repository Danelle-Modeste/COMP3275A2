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

public class Gyroscope extends AppCompatActivity {
    SensorManager sensorManager;
    SensorListener sensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sensorManager =(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        final Sensor gyroSensor = sensorManager.getDefaultSensor((Sensor.TYPE_GYROSCOPE));
        TextView disp_gyro= (TextView)findViewById(R.id.txt_gyro);

        sensorListener= new SensorListener(getApplicationContext(),disp_gyro);
        sensorManager.registerListener(sensorListener, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onDestroy(){
        super.onDestroy();
        sensorManager.unregisterListener(sensorListener);
    }
}
