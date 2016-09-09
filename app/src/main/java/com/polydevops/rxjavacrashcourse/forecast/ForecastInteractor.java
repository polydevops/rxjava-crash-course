package com.polydevops.rxjavacrashcourse.forecast;

import android.content.SharedPreferences;

import com.polydevops.rxjavacrashcourse.model.forecast.ForecastResponse;
import com.polydevops.rxjavacrashcourse.webService.WebServiceManager;

import rx.Observable;

/**
 * TODO: Add class header comment.
 */
public class ForecastInteractor implements ForecastContract.Interactor {

    private static final String PREF_FORECAST_CITY = "pref_forecast_city";

    private WebServiceManager webServiceManager;
    private SharedPreferences sharedPreferences;

    public ForecastInteractor(WebServiceManager webServiceManager, SharedPreferences sharedPreferences) {
        this.webServiceManager = webServiceManager;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Observable<ForecastResponse> getWeatherForecast(String city) {
        return webServiceManager.getForecast(city);
    }

    @Override
    public String getSavedForecastCity() {
        return sharedPreferences.getString(PREF_FORECAST_CITY, null);
    }

    @Override
    public void setSavedForecastCity(String city) {
        sharedPreferences.edit().putString(PREF_FORECAST_CITY, city).apply();
    }
}
