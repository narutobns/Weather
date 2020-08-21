package com.example.weather.logic.network;

import android.os.AsyncTask;

import com.example.weather.logic.Repository;

import java.util.List;

public class WeatherNetwork extends AsyncTask<Void, Void, String> {
    private String url;
    private HttpCallback callback;
    private String TAG = "WeatherNetwork";
    private String requestMethod;

    public WeatherNetwork(HttpCallback callback, String url, String requestMethod){
        this.callback = callback;
        this.url = url;
        this.requestMethod = requestMethod;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpUtil httpUtil = new HttpUtil();
        String response = httpUtil.HttpURLConnection(url,requestMethod);
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        if (response != null) {
            if (callback != null) {
                callback.onFinish(response);
            }
        } else if (callback != null) {
            callback.onError(TAG);
        }
    }
}
