package com.example.weatherapp.forecastDetails.fragment.model.mapper;

import android.content.res.Resources;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysOneTimeWeatherForecastResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.forecast.fullForecast.DayForecastDisplayModel;
import com.example.weatherapp.data.model.forecast.fullForecast.IDayForecastDisplayModel;
import com.example.weatherapp.data.model.forecast.fullForecast.IOneTimeForecastDisplayModel;
import com.example.weatherapp.data.model.forecast.fullForecast.ISeveralDaysForecastDisplayModel;
import com.example.weatherapp.data.model.forecast.fullForecast.OneTimeForecastDisplayModel;
import com.example.weatherapp.data.model.forecast.fullForecast.SeveralDaysForecastDisplayModel;
import com.example.weatherapp.forecastDetails.fragment.model.mapper.exception.DamagedForecastResponseException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.weatherapp.util.UtilDateTime.dateToHnMm;
import static com.example.weatherapp.util.UtilDateTime.getTodayDate;
import static com.example.weatherapp.util.UtilDateTime.getTomorrowDayDate;
import static com.example.weatherapp.util.UtilDateTime.getWeekDayFromDate;
import static com.example.weatherapp.util.UtilDateTime.parseToLocalDate;
import static com.example.weatherapp.util.UtilDateTime.parseToLocalDateTime;
import static com.example.weatherapp.util.UtilWeatherDisplayFormat.formatToDisplayValueHumidity;
import static com.example.weatherapp.util.UtilWeatherDisplayFormat.formatToDisplayValuePressure;
import static com.example.weatherapp.util.UtilWeatherDisplayFormat.formatToDisplayValueTemp;
import static com.example.weatherapp.util.UtilWeatherDisplayFormat.formatToDisplayValueWindSpeed;
import static com.example.weatherapp.util.UtilWeatherIcon.getOpenWeatherIconUrl;

public class SeveralDaysForecastMapper implements ISeveralDaysForecastMapper {
    private final Resources resources;
    private final Date todayDate;
    private final Date tomorrowDate;

    public SeveralDaysForecastMapper(Resources resources) {
        this.resources = resources;
        this.todayDate = getTodayDate();
        this.tomorrowDate = getTomorrowDayDate();
    }

    @Override
    public ISeveralDaysForecastDisplayModel map(ISeveralDaysWeatherResponse response) {
        try {
            return getModel(response);
        } catch (Exception e) {
            throw new DamagedForecastResponseException(e);
        }
    }

    private ISeveralDaysForecastDisplayModel getModel(ISeveralDaysWeatherResponse response) throws ParseException {
        List<IDayForecastDisplayModel> dayForecastDisplayModels = new ArrayList<>();
        Date latestMappedForecastData = null;
        List<IOneTimeForecastDisplayModel> latestMappedForecastDisplayModels = null;

        for (int i = 0; i < response.getForecastList().length; i++) {
            ISeveralDaysOneTimeWeatherForecastResponse forecastResponse = response.getForecastList()[i];

            if (latestMappedForecastData == null) {
                latestMappedForecastData = parseToLocalDate(forecastResponse.getDate());
            }
            if (latestMappedForecastDisplayModels == null) {
                latestMappedForecastDisplayModels = new ArrayList<>();
            }

            Date forecastDate = parseToLocalDate(forecastResponse.getDate());

            if (forecastDate.after(latestMappedForecastData)) {
                dayForecastDisplayModels.add(
                        new DayForecastDisplayModel(
                                latestMappedForecastDisplayModels,
                                getTitleByDate(latestMappedForecastData)));

                latestMappedForecastData = forecastDate;
                latestMappedForecastDisplayModels = new ArrayList<>();
            }


            latestMappedForecastDisplayModels.add(getOneTimeForecast(forecastResponse));
        }

        return new SeveralDaysForecastDisplayModel(dayForecastDisplayModels);
    }


    private IOneTimeForecastDisplayModel getOneTimeForecast(
            ISeveralDaysOneTimeWeatherForecastResponse response) throws ParseException {

        Date forecastDateTime = parseToLocalDateTime(response.getDate());

        return
                new OneTimeForecastDisplayModel(
                        formatToDisplayValueTemp(response.getMain().getTemp()),
                        dateToHnMm(forecastDateTime),
                        formatToDisplayValuePressure(response.getMain().getPressure()),
                        formatToDisplayValueWindSpeed(response.getWind().getSpeed()),
                        formatToDisplayValueHumidity(response.getMain().getHumidity()),
                        getOpenWeatherIconUrl(response.getWeather()[0].getIconName()));
    }

    private String getTitleByDate(Date date) {

        if (date.getTime() == todayDate.getTime()) {
            return resources.getString(R.string.forecast_details_today);
        } else if (date.getTime() == tomorrowDate.getTime()) {
            return resources.getString(R.string.forecast_details_tomorrow);
        } else {
            return getWeekDayFromDate(date);
        }
    }
}
