package com.example.weather.logic;

import android.text.TextUtils;

import com.example.weather.logic.model.DailyResponse;
import com.example.weather.logic.model.PlaceResponse;
import com.example.weather.logic.model.RealtimeResponse;
import com.example.weather.logic.network.HttpCallback;
import com.example.weather.logic.network.WeatherNetwork;
import com.example.weather.ui.weather.WeatherCallback;
import com.google.gson.Gson;



public class Repository implements HttpCallback {
    private String url =  "https://api.caiyunapp.com/v2.5/gTp7dBJS3cGkAxF1/";
    private String requestMethod = "GET";
    private DailyResponse.Daily daily;
    private RealtimeResponse.Realtime realtime;
    private WeatherCallback weatherCallback;

    public Repository(WeatherCallback weatherCallback) {
        this.weatherCallback = weatherCallback;
    }

    public static PlaceResponse handlePlace(String response) {
        PlaceResponse places = new PlaceResponse();
        if (!TextUtils.isEmpty(response)) {
            Gson gson = new Gson();
            places = gson.fromJson(response,PlaceResponse.class);
        }
        return places;
    }

    public void getWeather(String locationLng, String locationLat) {
        String realTimeUrl = url + locationLng + ',' + locationLat + "/realtime.json";
        WeatherNetwork weatherNetwork = new WeatherNetwork(Repository.this, realTimeUrl, requestMethod);
        weatherNetwork.execute();
        String dailyUrl = url + locationLng + ',' + locationLat + "/daily.json";
        WeatherNetwork weatherNetwork1 = new WeatherNetwork(Repository.this, dailyUrl, requestMethod);
        weatherNetwork1.execute();
    }

    public DailyResponse handleDaily(String response) {
        if (!TextUtils.isEmpty(response)) {
            Gson gson = new Gson();
            return gson.fromJson(response,DailyResponse.class);
        }
        return null;
    }

    public RealtimeResponse handleRealtime(String response){
        if (!TextUtils.isEmpty(response)){
            Gson gson = new Gson();
            return gson.fromJson(response,RealtimeResponse.class);
        }
        return null;
    }

    public DailyResponse.Daily getDaily() {
        return daily;
    }

    public RealtimeResponse.Realtime getRealtime() {
        return realtime;
    }

    @Override
    public void onFinish(String... response) {
        if (response[1].endsWith("daily.json")) {
            daily = handleDaily(response[0]).result.daily;
            weatherCallback.onDaily(daily);
        } else {
            realtime = handleRealtime(response[0]).result.realtime;
            weatherCallback.onRealtime(realtime);
        }
    }

    @Override
    public void onError(String TAG) {

    }
}
