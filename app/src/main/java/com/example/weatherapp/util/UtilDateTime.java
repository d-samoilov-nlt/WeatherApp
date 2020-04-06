package com.example.weatherapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UtilDateTime {

    public static String dateToHnMm(Date date) {
        SimpleDateFormat dateToTime = new SimpleDateFormat("h:mm", Locale.getDefault());
        return dateToTime.format(date);
    }

    public static Date parseToLocalDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.parse(date);
    }

    public static Date parseToLocalDateTime(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.parse(date);
    }
}
