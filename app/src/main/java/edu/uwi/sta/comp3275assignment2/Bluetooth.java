package edu.uwi.sta.comp3275assignment2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Bluetooth extends AppCompatActivity {
    BluetoothAdapter bluAdapter;
    ListView device_list ;
    TextView status ;
    final int ACTION_REQUEST_ENABLE =1;
    BroadcastReceiver bluReceiver;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bluAdapter = BluetoothAdapter.getDefaultAdapter();
        device_list =(ListView)findViewById(R.id.list_devices);
        status =(TextView)findViewById(R.id.txt_status);
        adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        device_list.setAdapter(adapter);
    }

    public int checkStatus(View view){
        String stat;
        int bluStat;
        if(bluAdapter == null) {
            stat="Bluetooth Not Supoorted";
            bluStat=-1;
        }
        else if(bluAdapter.isEnabled()){
            stat="Bluetooth is Enabled";
            bluStat=1;
        }
        else{
            stat="Bluetooth is Disabled";
            bluStat=0;
        }
        status.setText(stat);
        return bluStat;
    }

    public void findDevices(View view){
        adapter.clear();
        adapter.notifyDataSetChanged();

        int bluStat=checkStatus(view);
        if(bluStat==-1){
            Toast.makeText(this,"Can Not Scan For Devices. Bluetooth is not Supported",Toast.LENGTH_LONG).show();
        }
        else {
            if (!bluAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, ACTION_REQUEST_ENABLE);
            }
            if (bluAdapter.isEnabled()) {

                if (bluAdapter.isDiscovering()) {
                    bluAdapter.cancelDiscovery();
                    unregisterReceiver(bluReceiver);
                    if(adapter!= null){
                        device_list.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.clear();
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                }

                Toast.makeText(this, "Scanning For Devices", Toast.LENGTH_SHORT).show();
                bluReceiver = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {

                        String action = intent.getAction();
                        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                            final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                            device_list.post(new Runnable() {
                                @Override
                                public void run() {
                                    Object x;
                                    x=device.getName()+"   "+device.getAddress();
                                    if(adapter.getPosition(x)==-1){
                                        adapter.add(x);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    }
                };

                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                if(adapter!= null){
                    device_list.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clear();
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
                registerReceiver(bluReceiver, filter);
                bluAdapter.startDiscovery();
            }
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        bluAdapter.cancelDiscovery();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        bluAdapter.cancelDiscovery();
        unregisterReceiver(bluReceiver);
        bluAdapter.disable();

        Toast.makeText(this,"Bluetooth has been turned off",Toast.LENGTH_SHORT).show();
    }
}
