package com.example.weather.ui.weather;

import androidx.lifecycle.ViewModel;

import com.example.weather.logic.Repository;
import com.example.weather.logic.model.DailyResponse;
import com.example.weather.logic.model.RealtimeResponse;
import com.example.weather.logic.model.Weather;


public class WeatherViewModel extends ViewModel implements WeatherCallback {
    private String locationLng;
    private String locationLat;
    private String placeName;
    private Weather weather = new Weather();
    private Callback callback;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Callback callback) {
        this.callback = callback;
        Repository repository = new Repository(WeatherViewModel.this);
        repository.getWeather(locationLng,locationLat);
    }

    public void setLocationLng(String locationLng) {
        this.locationLng = locationLng;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Override
    public void onDaily(DailyResponse.Daily daily) {
        weather.daily = daily;
        callback.onWeather(weather);
    }

    @Override
    public void onRealtime(RealtimeResponse.Realtime realtime) {
        weather.realtime = realtime;
        callback.onWeather(weather);
    }
}
