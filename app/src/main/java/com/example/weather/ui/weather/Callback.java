package com.example.weather.ui.weather;

import com.example.weather.logic.model.Weather;

public interface Callback {
    void onWeather(Weather weather);
}
