package com.example.weather.logic.network;

public interface HttpCallback {
    void onFinish(String response);
    void onError(String TAG);
}
