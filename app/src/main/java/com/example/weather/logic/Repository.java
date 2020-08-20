package com.example.weather.logic;

import android.text.TextUtils;

import com.example.weather.logic.network.ResponseBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    public static List<ResponseBean> handelProvince(String response) {
        List<ResponseBean> responseBeanList = new ArrayList<>();
        if(!TextUtils.isEmpty(response)) {
            try{
                JSONArray provinces = new JSONArray(response);
                for (int i = 0; i < provinces.length(); i++){
                    JSONObject provinceObject = provinces.getJSONObject(i);
                    String provinceName = provinceObject.getString("name");
                    int provinceId = provinceObject.getInt("id");
                    responseBeanList.add(new ResponseBean(provinceName,provinceId));
                }
            } catch ( JSONException e) {
                e.printStackTrace();
            }
        }
        return responseBeanList;
    }

    public static List<ResponseBean> handelCities(String response) {
        List<ResponseBean> responseBeanList = new ArrayList<>();
        if(!TextUtils.isEmpty(response)) {
            try{
                JSONArray cities = new JSONArray(response);
                for (int i = 0; i < cities.length(); i++){
                    JSONObject cityObject = cities.getJSONObject(i);
                    String cityName = cityObject.getString("name");
                    int cityId = cityObject.getInt("id");
                    responseBeanList.add(new ResponseBean(cityName,cityId));
                }
            } catch ( JSONException e) {
                e.printStackTrace();
            }
        }
        return responseBeanList;
    }

    public static List<ResponseBean> handelCounties(String response) {
        List<ResponseBean> responseBeanList = new ArrayList<>();
        if(!TextUtils.isEmpty(response)) {
            try{
                JSONArray counties = new JSONArray(response);
                for (int i = 0; i < counties.length(); i++){
                    JSONObject countyObject = counties.getJSONObject(i);
                    String countyName = countyObject.getString("name");
                    int countyId = countyObject.getInt("id");
                    responseBeanList.add(new ResponseBean(countyName,countyId));
                }
            } catch ( JSONException e) {
                e.printStackTrace();
            }
        }
        return responseBeanList;
    }
}
