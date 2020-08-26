package com.example.weather.ui.weather;

import com.example.weather.logic.model.DailyResponse;
import com.example.weather.logic.model.RealtimeResponse;

public interface WeatherCallback {
    void onDaily(DailyResponse.Daily daily);
    void onRealtime(RealtimeResponse.Realtime realtime);
}
