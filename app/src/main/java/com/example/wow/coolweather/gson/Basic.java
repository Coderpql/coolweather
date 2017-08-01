package com.example.wow.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wow on 2017/7/31.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
