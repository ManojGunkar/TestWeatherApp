package com.manoj.weatherapp.repository;

import com.manoj.weatherapp.apiconnector.Client;
import com.manoj.weatherapp.apiconnector.response.CurrentWeather;
import com.manoj.weatherapp.apiconnector.response.ForcastWeather;
import com.manoj.weatherapp.utils.Utils;

import io.reactivex.Single;

public class WeatherRepository {

    private Client.Service mService;

    public WeatherRepository() {
        this.mService = Client.getClient();
    }

    public Single<CurrentWeather> executeCurrentWeatherApi(String city) {
        return mService.getCurrentWeather(Utils.API_KEY, city);
    }

    public Single<ForcastWeather> executeForcastWeatherApi(String city) {
        return mService.getForecastWeather(Utils.API_KEY, city, String.valueOf(7));
    }
}
