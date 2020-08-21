package com.example.weather.logic;

import android.text.TextUtils;

import com.example.weather.logic.model.PlaceResponse;
import com.google.gson.Gson;



public class Repository {

    public static PlaceResponse handelPlace(String response) {
        PlaceResponse places = new PlaceResponse();
        if(!TextUtils.isEmpty(response)) {
            Gson gson = new Gson();
            places = gson.fromJson(response,PlaceResponse.class);
        }
        return places;
    }

}
