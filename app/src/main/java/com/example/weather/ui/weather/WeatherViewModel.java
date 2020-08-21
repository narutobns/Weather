package com.example.weather.ui.weather;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather.logic.model.Location;

public class WeatherViewModel extends ViewModel {
    private MutableLiveData<Location> locationLiveData;
}
