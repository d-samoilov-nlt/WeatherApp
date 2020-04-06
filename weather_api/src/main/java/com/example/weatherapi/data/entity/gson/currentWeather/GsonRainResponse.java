package com.example.weatherapi.data.entity.gson.currentWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IRainResponse;
import com.google.gson.annotations.SerializedName;

public class GsonRainResponse implements IRainResponse {
    @SerializedName("3h")
    private double rainVolume;

    @Override
    public double getRainVolume() {
        return rainVolume;
    }
}
