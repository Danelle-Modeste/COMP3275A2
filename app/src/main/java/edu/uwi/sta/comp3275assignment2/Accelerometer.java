package edu.uwi.sta.comp3275assignment2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import edu.uwi.sta.comp3275assignment2.Listeners.SensorListener;

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
        TextView disp_accel_x= (TextView)findViewById(R.id.txt_accel_x);
        TextView disp_accel_y= (TextView)findViewById(R.id.txt_accel_y);
        TextView disp_accel_z= (TextView)findViewById(R.id.txt_accel_z);

        sensorListener= new SensorListener(getApplicationContext(),disp_accel_x,disp_accel_y,disp_accel_z,3);
//        sensorManager.registerListener(sensorListener,accelSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorListener,accelSensor,5);
    }

    protected void onDestroy(){
        super.onDestroy();
        sensorManager.unregisterListener(sensorListener);
    }
}
