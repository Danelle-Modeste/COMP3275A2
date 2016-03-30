package edu.uwi.sta.comp3275assignment2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.widget.Toast;

public class SensorListener implements SensorEventListener {
    Context context;
    TextView tv;

    public SensorListener(Context context,TextView tv){
        this.context=context;
        this.tv=tv;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        tv.setText(String.valueOf(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
