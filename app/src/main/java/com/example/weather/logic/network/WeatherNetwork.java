package com.example.weather.logic.network;

import android.os.AsyncTask;

import com.example.weather.logic.Repository;

import java.util.List;

public class WeatherNetwork extends AsyncTask<Void, Void, List<ResponseBean>> {
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
    protected List<ResponseBean> doInBackground(Void... voids) {
        HttpUtil httpUtil = new HttpUtil();
        String response = httpUtil.HttpURLConnection(url,requestMethod);
        return Repository.handelProvince(response);
    }

    @Override
    protected void onPostExecute(List<ResponseBean> beanList) {
        if (beanList != null) {
            if (callback != null) {
                callback.onFinish(beanList);
            }
        } else if (callback != null) {
            callback.onError(TAG);
        }
    }
}
