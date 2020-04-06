package com.example.weatherapp.util;

import android.net.Uri;

public class UtilWeatherIcon {
    public static Uri getOpenWeatherIconUrl(String iconName) {
        return Uri.parse("https://openweathermap.org/img/wn/" + iconName + ".png");
    }
}
