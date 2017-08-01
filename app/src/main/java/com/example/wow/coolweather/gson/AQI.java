package com.example.wow.coolweather.gson;

/**
 * Created by wow on 2017/7/31.
 */

public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
