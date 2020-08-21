package com.example.weather.logic.model;

import com.google.gson.annotations.SerializedName;

public class Place {
    public String name;
    public Location location;
    @SerializedName("formatted_address")
    public String address;
}
