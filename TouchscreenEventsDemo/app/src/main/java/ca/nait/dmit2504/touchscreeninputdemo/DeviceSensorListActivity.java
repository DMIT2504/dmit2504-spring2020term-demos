package ca.nait.dmit2504.touchscreeninputdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Steps for implementing Swipe-to-Refresh
 * 1. Add the SwipeRefreshLayout widget
 * 2. Include the ListView within the SwipeRefreshLayout
 * 3. Add the onRefreshListener to call your refresh method
 * 4. Call the setRefreshing(false) after completing your update.
 */
public class DeviceSensorListActivity extends AppCompatActivity {

    // Step 2a: Define variables for managing swipe refresh
    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView mDeviceSensorListView;
    List<String> mDeviceSensorList = new ArrayList<>();

    // Step 3: Define a method to handle the refresh
    private void refreshListView() {
        mDeviceSensorList.add("Refreshed at " + new Date().toString());
        ListAdapter sensorAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDeviceSensorList);
        mDeviceSensorListView.setAdapter(sensorAdaptor);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_sensor_list);

        // Step 4: Add code to handle swipe to refresh
        mSwipeRefreshLayout = findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshListView();
            }
        });

        // Find the ListView in the layout
        mDeviceSensorListView = findViewById(R.id.device_sensor_list);
        // Get a SensorManager from the Android OS
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Get a list of sensors from the SensorManager
        // Sensors are grouped into three categories: Motion Sensors, Environmental Sensors, Position sensors
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        // Add the name of each sensor to mDeviceSensorList
        for (Sensor currentSensor : sensors) {
            mDeviceSensorList.add(currentSensor.getName());
        }
        // Create an Adapter for the ListView
        ListAdapter sensorAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDeviceSensorList);
        mDeviceSensorListView.setAdapter(sensorAdaptor);

    }
}