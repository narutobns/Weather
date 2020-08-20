package com.example.weather.logic.network;

import java.util.List;

public interface HttpCallback {
    void onFinish(List<ResponseBean> response);
    void onError(String TAG);
}
