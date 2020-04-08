package com.example.weatherapp.forecastDetails.fragment.model.mapper;

import android.content.res.Resources;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysOneTimeWeatherForecastResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.forecast.fullForecast.DayForecastDisplayModel;
import com.example.weatherapp.data.model.forecast.fullForecast.IDayForecastDisplayModel;
import com.example.weatherapp.data.model.forecast.fullForecast.IOneTimeForecastDisplayModel;
import com.example.weatherapp.data.model.forecast.fullForecast.OneTimeForecastDisplayModel;
import com.example.weatherapp.forecastDetails.fragment.model.mapper.exception.UnsupportedForecastDateException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.weatherapp.util.UtilDateTime.dateToHnMm;
import static com.example.weatherapp.util.UtilDateTime.getTomorrowDayDate;
import static com.example.weatherapp.util.UtilDateTime.isTodayDate;
import static com.example.weatherapp.util.UtilDateTime.parseToLocalDate;
import static com.example.weatherapp.util.UtilDateTime.parseToLocalDateTime;
import static com.example.weatherapp.util.UtilWeatherDisplayFormat.formatToDisplayValueHumidity;
import static com.example.weatherapp.util.UtilWeatherDisplayFormat.formatToDisplayValuePressure;
import static com.example.weatherapp.util.UtilWeatherDisplayFormat.formatToDisplayValueTemp;
import static com.example.weatherapp.util.UtilWeatherDisplayFormat.formatToDisplayValueWindSpeed;
import static com.example.weatherapp.util.UtilWeatherIcon.getOpenWeatherIconUrl;

public class TomorrowForecastMapper implements IDayForecastMapper {
    private final Resources resources;

    private Date tomorrowDate;

    public TomorrowForecastMapper(Resources resources) {
        this.resources = resources;
    }

    @Override
    public IDayForecastDisplayModel map(ISeveralDaysWeatherResponse response) {
        tomorrowDate = getTomorrowDayDate();

        return getModel(response);
    }

    private IDayForecastDisplayModel getModel(ISeveralDaysWeatherResponse response) {
        List<IOneTimeForecastDisplayModel> forecastDisplayModels = new ArrayList<>();

        for (int i = 0; i < response.getForecastList().length; i++) {
            ISeveralDaysOneTimeWeatherForecastResponse
                    oneTimeWeatherForecastResponse = response.getForecastList()[i];

            try {
                if (!isTodayDate(oneTimeWeatherForecastResponse.getDate())) {
                    forecastDisplayModels.add(
                            getOneTimeForecast(oneTimeWeatherForecastResponse));
                }

            } catch (UnsupportedForecastDateException e) {
                return new DayForecastDisplayModel(forecastDisplayModels, getTitle());
            }
        }

        return new DayForecastDisplayModel(forecastDisplayModels, getTitle());
    }

    private IOneTimeForecastDisplayModel getOneTimeForecast(
            ISeveralDaysOneTimeWeatherForecastResponse response) {

        Date forecastDate;

        try {
            forecastDate = parseToLocalDate(response.getDate());
        } catch (ParseException e) {
            throw new UnsupportedForecastDateException(e.getMessage());
        }
        if (!tomorrowDate.equals(forecastDate)) {
            throw new UnsupportedForecastDateException();
        }

        Date forecastDateTime;

        try {
            forecastDateTime = parseToLocalDateTime(response.getDate());
        } catch (ParseException e) {
            throw new UnsupportedForecastDateException(e.getMessage());
        }

        return
                new OneTimeForecastDisplayModel(
                        formatToDisplayValueTemp(response.getMain().getTemp()),
                        dateToHnMm(forecastDateTime),
                        formatToDisplayValuePressure(response.getMain().getPressure()),
                        formatToDisplayValueWindSpeed(response.getWind().getSpeed()),
                        formatToDisplayValueHumidity(response.getMain().getHumidity()),
                        getOpenWeatherIconUrl(response.getWeather()[0].getIconName()));
    }

    private String getTitle() {
        return resources.getString(R.string.forecast_details_tomorrow);
    }
}
