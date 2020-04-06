package com.example.weatherapp.util;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UtilDateTime {

    public static String dateToHnMm(Date date) {
        SimpleDateFormat dateToTime = new SimpleDateFormat("H:mm", Locale.getDefault());
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

    public static Date getTomorrowDayDate() {
        return getNextDayDate(getTodayDate());
    }

    private static Date getNextDayDate(Date origin) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(origin);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static Date getTodayDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static String getWeekDayFromDate(Date date) {
        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(date);
    }

    public static boolean isTodayDate(String date) {
        try {
            Date forecastDate = parseToLocalDate(date);

            return DateUtils.isToday(forecastDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
