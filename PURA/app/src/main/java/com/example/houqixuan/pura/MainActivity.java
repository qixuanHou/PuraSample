package com.example.houqixuan.pura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ubhave.datahandler.ESDataManager;
import com.ubhave.datahandler.except.DataHandlerException;
import com.ubhave.datahandler.loggertypes.AbstractDataLogger;
import com.ubhave.datahandler.transfer.DataUploadCallback;
import com.ubhave.sensormanager.ESException;
import com.ubhave.sensormanager.ESSensorManager;
import com.ubhave.sensormanager.data.SensorData;
import com.ubhave.sensormanager.data.pull.AccelerometerData;
import com.ubhave.sensormanager.sensors.SensorUtils;

public class MainActivity extends AppCompatActivity {

    private ESSensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView newtext = (TextView) findViewById(R.id.acc);
        try {
            sensorManager = ESSensorManager.getSensorManager(this);
            AccelerometerData d = (AccelerometerData) sensorManager.getDataFromSensor(SensorUtils.SENSOR_TYPE_ACCELEROMETER);

            newtext.setText(d.getSensorReadings().toString());

        } catch (ESException e) {
            e.printStackTrace();
            newtext.setText("fail");
        }


    }
}

