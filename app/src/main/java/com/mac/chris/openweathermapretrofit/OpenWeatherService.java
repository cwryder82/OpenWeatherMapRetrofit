package com.mac.chris.openweathermapretrofit;

import com.mac.chris.openweathermapretrofit.data.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chris on 4/28/16.
 */
public interface OpenWeatherService {

    @GET("/data/2.5/forcast/daily?q=Atlanta&mode=json&units=metric&cnt=7&appid=a834182bf0de3d05f5887b94e4f14b96")
    Call<WeatherData> listWeather();

}
