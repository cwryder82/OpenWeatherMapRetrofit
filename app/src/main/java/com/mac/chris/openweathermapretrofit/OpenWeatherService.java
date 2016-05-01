package com.mac.chris.openweathermapretrofit;

import com.mac.chris.openweathermapretrofit.data.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {

    @GET("data/2.5/forecast/daily?q=Atlanta&mode=json&units=metric&cnt=7&appid=a834182bf0de3d05f5887b94e4f14b96")
    Call<WeatherData> listWeather();

    @GET("data/2.5/forecast/daily")
    Call<WeatherData> listWeather(@Query("q") String q,
                                  @Query("mode") String mode,
                                  @Query("units") String units,
                                  @Query("cnt") String cnt,
                                  @Query("appid") String appid);

}
