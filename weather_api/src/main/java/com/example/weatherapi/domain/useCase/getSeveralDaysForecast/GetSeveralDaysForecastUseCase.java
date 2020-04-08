package com.example.weatherapi.domain.useCase.getSeveralDaysForecast;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapi.service.interfaces.IOpenWeatherApi;

import static com.example.weatherapi.util.UtilsRequestUrlOptions.getUrlOptions;

public class GetSeveralDaysForecastUseCase implements IGetSeveralDaysForecastUseCase {
    private final IOpenWeatherApi openWeatherApi;

    public GetSeveralDaysForecastUseCase(IOpenWeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    @Override
    public ISeveralDaysWeatherResponse get(ICityLocation location) {
        return get(location, ForecastUnitsType.CELSIUS.getValue());
    }

    @Override
    public ISeveralDaysWeatherResponse get(ICityLocation location, int unitType) {
        return openWeatherApi.getFiveDaysForecast(getUrlOptions(location, unitType));
    }
}
