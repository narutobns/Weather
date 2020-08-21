package com.example.weather.ui.place;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather.logic.Repository;
import com.example.weather.logic.model.Place;
import com.example.weather.logic.model.PlaceResponse;
import com.example.weather.logic.network.ResponseBean;
import com.google.gson.Gson;

import java.util.List;

public class PlaceViewModel extends ViewModel {
    private MutableLiveData<List<Place>> response = new MutableLiveData<>();

    public MutableLiveData<List<Place>> getResponse() {
        return response;
    }

    public void setResponse(String response) {
        PlaceResponse places = Repository.handelPlace(response);
        this.response.setValue(places.places);
    }
}
