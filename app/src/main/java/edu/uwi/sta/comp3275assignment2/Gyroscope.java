package edu.uwi.sta.comp3275assignment2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import edu.uwi.sta.comp3275assignment2.Listeners.SensorListener;

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

        TextView disp_gyro_x= (TextView)findViewById(R.id.txt_gyro_x);
        TextView disp_gyro_y= (TextView)findViewById(R.id.txt_gyro_y);
        TextView disp_gyro_z= (TextView)findViewById(R.id.txt_gyro_z);

        sensorListener= new SensorListener(getApplicationContext(),disp_gyro_x,disp_gyro_y,disp_gyro_z,3);

        sensorManager.registerListener(sensorListener, gyroSensor, 5);
       // sensorManager.registerListener(sensorListener, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onDestroy(){
        super.onDestroy();
        sensorManager.unregisterListener(sensorListener);
    }
}
