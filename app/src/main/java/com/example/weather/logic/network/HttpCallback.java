package com.example.weather.logic.network;

import java.util.List;

public interface HttpCallback {
    void onFinish(String response);
    void onError(String TAG);
}
