package edu.uwi.sta.comp3275assignment2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import edu.uwi.sta.comp3275assignment2.Listeners.SensorListener;

public class Proximity extends AppCompatActivity {
    SensorManager sensorManager ;
    SensorListener sensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sensorManager =(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor proxSensor = sensorManager.getDefaultSensor((Sensor.TYPE_PROXIMITY));
        TextView disp_prox= (TextView)findViewById(R.id.txt_prox);

        sensorListener= new SensorListener(getApplicationContext(),disp_prox,1);
        sensorManager.registerListener(sensorListener, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onDestroy(){
        super.onDestroy();
        sensorManager.unregisterListener(sensorListener);
    }

}
