package com.polydevops.rxjavacrashcourse.webService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polydevops.rxjavacrashcourse.model.forecast.ForecastResponse;
import com.polydevops.rxjavacrashcourse.model.weather.WeatherResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Contains all web service calls
 */
public class WebServiceManager {

    private static final String KEY = "802631c2e893a5442a9f8b877c8bfbf6";
    private IWeatherApi weatherService;

    public WebServiceManager() {
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(WeatherResponse.class, new WeatherDeserializer())
                .registerTypeAdapter(ForecastResponse.class, new ForecastDeserializer())
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IWeatherApi.ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        weatherService = retrofit.create(IWeatherApi.class);
    }

    public Observable<WeatherResponse> getWeather(final String city) {
        return weatherService.getWeather(city + ",us", "imperial", KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ForecastResponse> getForecast(final String city) {
        return weatherService.getForecast(city + ",us", "imperial", KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
