package com.example.houqixuan.sensordemp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mPressure;
    private Sensor mLight;
    private Sensor mTemperature;
    private Sensor mHumidity;


    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t = (TextView) findViewById(R.id.temperature);
        TextView l = (TextView) findViewById(R.id.light);
        TextView p = (TextView) findViewById(R.id.pressure);
        TextView h = (TextView) findViewById(R.id.humidity);
        TextView d = (TextView) findViewById(R.id.date);


        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
//        t.setText(""+mTemperature.getPower());


        Calendar c = Calendar.getInstance();
        d.setText(c.getTime().toString());


    }


    public void openMap(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {

        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {





//
//        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
//            float pressure = event.values[0];
//            String s = String.format("%f", pressure);
//            p.setText(s);
//        }
//        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
//            float light = event.values[0];
//            String s = String.format("%f", light);
//            l.setText(s);
//        }
//        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
//            float temperature = event.values[0];
//            String s = String.format("%f", temperature);
//            t.setText(s);
//        }

//        if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
//            float humidity = event.values[0];
//            String s = String.format("%f", humidity);
//            h.setText(s);
//        }

        // Do something with this sensor data.
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}