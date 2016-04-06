package edu.uwi.sta.comp3275assignment2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class GPSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().matches("android.intent.action.BOOT_COMPLETED")){
            if((ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) &&
                    (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
                Toast.makeText(context, "COMP3275A2 requires location permissions to store GPS locations. Please enter GPS Activity to enable them",Toast.LENGTH_LONG).show();
            }
            else {
                Intent pushIntent = new Intent(context, LocationService.class);
                context.startService(pushIntent);
            }
        }
    }

}