package com.example.weather.ui.weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.logic.model.DailyResponse;
import com.example.weather.logic.model.RealtimeResponse;
import com.example.weather.logic.model.Sky;
import com.example.weather.logic.model.Weather;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity implements Callback{
    private WeatherViewModel weatherViewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_weather);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherViewModel.setLocationLng(getIntent().getStringExtra("location_lng"));
        weatherViewModel.setLocationLat(getIntent().getStringExtra("location_lat"));
        weatherViewModel.setPlaceName(getIntent().getStringExtra("place_name"));
        weatherViewModel.setWeather(WeatherActivity.this);
    }

    private void showWeatherInfo(Weather weather) {
        RealtimeResponse.Realtime realtime = weather.realtime;
        DailyResponse.Daily daily = weather.daily;
        TextView placeName = findViewById(R.id.placeName);
        placeName.setText(weatherViewModel.getPlaceName());
        String currentTempText = realtime.temperature + "℃";
        TextView currentTemp = findViewById(R.id.currentTemp);
        currentTemp.setText(currentTempText);
        TextView currentSky = findViewById(R.id.currentSky);
        currentSky.setText(Sky.getSky(realtime.skycon).getInfo());
        String currentPM25Text = "空气指数" + realtime.airQuality.aqi.chn;
        TextView currentAQI = findViewById(R.id.currentAQI);
        currentAQI.setText(currentPM25Text);
        findViewById(R.id.nowLayout).setBackgroundResource(Sky.getSky(realtime.skycon).getBg());
        LinearLayout forecastLayout = findViewById(R.id.forecastLayout);
        forecastLayout.removeAllViews();
        int days = daily.skycon.size();
        for (int i = 0; i < days; i++) {
            DailyResponse.Skycon skycon = daily.skycon.get(i);
            DailyResponse.Temperature temperature = daily.temperature.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false);
            TextView dateInfo = view.findViewById(R.id.dateInfo);
            ImageView skyIcon = view.findViewById(R.id.skyIcon);
            TextView skyInfo = view.findViewById(R.id.skyInfo);
            TextView temperatureInfo = view.findViewById(R.id.temperatureInfo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            dateInfo.setText(simpleDateFormat.format(skycon.date));
            Sky sky = Sky.getSky(skycon.value);
            skyIcon.setImageResource(sky.getIcon());
            skyInfo.setText(sky.getInfo());
            String tempText = temperature.min + "~" + temperature.max + "℃";
            temperatureInfo.setText(tempText);
            forecastLayout.addView(view);
        }
        DailyResponse.LifeIndex lifeIndex = daily.lifeIndex;
        TextView coldRiskText = findViewById(R.id.coldRiskText);
        coldRiskText.setText(lifeIndex.coldRisk.get(0).desc);
        TextView dressingText = findViewById(R.id.dressingText);
        dressingText.setText(lifeIndex.dressing.get(0).desc);
        TextView ultravioletText = findViewById(R.id.ultravioletText);
        ultravioletText.setText(lifeIndex.ultraviolet.get(0).desc);
        TextView carWashing = findViewById(R.id.carWashingText);
        carWashing.setText(lifeIndex.carWashing.get(0).desc);
        ScrollView weatherLayout = findViewById(R.id.weatherLayout);
        weatherLayout.setVisibility(View.VISIBLE);
    }

    public static void actionStart(Context srcActivity, String lng, String lat, String name) {
        Intent intent = new Intent(srcActivity, WeatherActivity.class);
        intent.putExtra("location_lng",lng);
        intent.putExtra("location_lat",lat);
        intent.putExtra("place_name",name);
        srcActivity.startActivity(intent);
    }

    @Override
    public void onWeather(Weather weather) {
        if (weather.realtime != null && weather.daily != null) {
            showWeatherInfo(weather);
        }
    }
}