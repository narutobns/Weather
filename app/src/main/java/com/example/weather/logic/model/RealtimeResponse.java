package com.example.weather.logic.model;

import com.google.gson.annotations.SerializedName;

public class RealtimeResponse {
    public String status;
    public Result result;

    public class Result{
        public Realtime realtime;
    }

    public class Realtime{
        public double temperature;
        public String skycon;
        @SerializedName("air_quality")
        public AirQuality airQuality;
    }

    public class AirQuality{
        public AQI aqi;
    }

    public class AQI{
        public double chn;
    }
}
