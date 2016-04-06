package edu.uwi.sta.comp3275assignment2.Listeners;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class SensorListener implements SensorEventListener {
    Context context;
    TextView tv1,tv2,tv3;
    int numFields;

    public SensorListener(Context context,TextView tv1,TextView tv2,TextView tv3,int numFields){
        this.context=context;
        this.tv1=tv1;
        this.tv2=tv2;
        this.tv3=tv3;
        this.numFields=numFields;
    }

    public SensorListener(Context context, TextView tv1,int numFields){
        this.context=context;
        this.tv1=tv1;
        this.numFields=numFields;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        if(numFields==1){
            tv1.setText(String.valueOf(event.values[0]));
        }
        else if(numFields==3) {
            tv1.setText(String.valueOf(event.values[0]));
            tv2.setText(String.valueOf(event.values[1]));
            tv3.setText(String.valueOf(event.values[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
