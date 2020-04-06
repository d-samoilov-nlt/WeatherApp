package com.example.weatherapp.util;

public class UtilWeatherDisplayFormat {

    public static String formatToDisplayValueTemp(double temp) {
        double tempToFormat = temp;

        boolean isTempBelowZero = temp < 0;

        if (isTempBelowZero) {
            tempToFormat = Math.abs(tempToFormat);
        }

        return isTempBelowZero ? "-" : "+" + (int) tempToFormat;
    }

    public static String formatToDisplayValueWindSpeed(double speed) {
        return String.valueOf((int) speed);
    }

    public static String formatToDisplayValuePressure(double pressure) {
        return String.valueOf((int) pressure);
    }

    public static String formatToDisplayValueHumidity(double humidity) {
        return String.valueOf((int) humidity);
    }
}
