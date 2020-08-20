package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.weather.logic.network.HttpCallback;
import com.example.weather.logic.network.WeatherNetwork;

public class MainActivity extends AppCompatActivity implements HttpCallback, View.OnClickListener {
    private String url = "https://www.baidu.com";
    //private String response;
    private TextView responseText;
    private String requestMethod ="GET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = findViewById(R.id.response_text);
        WeatherNetwork weatherNetwork = new WeatherNetwork(MainActivity.this,url,requestMethod);
        weatherNetwork.execute();
    }

    @Override
    public void onFinish(String response) {
        responseText.setText(response);
    }

    @Override
    public void onError(String TAG) {
        Log.e(TAG,"Network Error");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            default:
                break;
        }

    }
}