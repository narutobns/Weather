package com.example.weather.ui.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.weather.R;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    public static void actionStart(Context srcActivity, String lng, String lat, String name) {
        Intent intent = new Intent(srcActivity, WeatherActivity.class);
        intent.putExtra("location_lng",lng);
        intent.putExtra("location_lat",lat);
        intent.putExtra("place_name",name);
        srcActivity.startActivity(intent);
    }
}