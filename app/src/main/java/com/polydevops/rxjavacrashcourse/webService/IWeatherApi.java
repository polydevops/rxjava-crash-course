package com.polydevops.rxjavacrashcourse.webService;

import com.polydevops.rxjavacrashcourse.model.forecast.ForecastResponse;
import com.polydevops.rxjavacrashcourse.model.weather.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Retrofit interface fo rinteracting with openweathermap API
 */
public interface IWeatherApi {

    String ENDPOINT = "http://api.openweathermap.org/data/2.5/";
    String PATH_WEATHER = "weather";
    String PATH_FORECAST = "forecast/daily";

    String QUERY_CITY = "q";
    String QUERY_UNITS = "units";
    String QUERY_APPID = "appid";

    @GET(PATH_WEATHER)
    Observable<WeatherResponse> getWeather(@Query(QUERY_CITY) String city, @Query(QUERY_UNITS) String units, @Query(QUERY_APPID) String appId);

    @GET(PATH_FORECAST)
    Observable<ForecastResponse> getForecast(@Query(QUERY_CITY) String city, @Query(QUERY_UNITS) String units, @Query(QUERY_APPID) String appId);
}
