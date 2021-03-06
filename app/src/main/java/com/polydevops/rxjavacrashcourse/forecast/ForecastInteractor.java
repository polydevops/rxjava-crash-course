package com.polydevops.rxjavacrashcourse.forecast;

import android.content.SharedPreferences;

import com.polydevops.rxjavacrashcourse.database.ForecastDAO;
import com.polydevops.rxjavacrashcourse.model.forecast.ForecastResponse;
import com.polydevops.rxjavacrashcourse.model.forecast.ForecastWeather;
import com.polydevops.rxjavacrashcourse.webService.WebServiceManager;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Provides methods for interacting with the forecast cache and forecast API
 */
public class ForecastInteractor implements ForecastContract.Interactor {

    private static final String PREF_FORECAST_CITY = "pref_forecast_city";

    private WebServiceManager webServiceManager;
    private SharedPreferences sharedPreferences;
    private ForecastDAO forecastDAO;

    public ForecastInteractor(WebServiceManager webServiceManager,
                              SharedPreferences sharedPreferences,
                              ForecastDAO forecastDAO) {
        this.webServiceManager = webServiceManager;
        this.sharedPreferences = sharedPreferences;
        this.forecastDAO = forecastDAO;
    }

    @Override
    public Observable<List<ForecastWeather>> getCurrentForecast(String city) {
        return webServiceManager.getForecast(city)
                .map(new Func1<ForecastResponse, List<ForecastWeather>>() {
                    @Override
                    public List<ForecastWeather> call(ForecastResponse forecastResponse) {
                        if (forecastResponse != null && forecastResponse.getCode() == 200) {
                            return forecastResponse.getForecast();
                        } else {
                            return null;
                        }
                    }
                });
    }

    @Override
    public Observable<Integer> saveForecastCache(List<ForecastWeather> forecast) {
        return forecastDAO.saveForecastCache(forecast)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<ForecastWeather>> getForecastCache() {
        return forecastDAO.getForecastCache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Integer> deleteForecastCache() {
        return forecastDAO.deleteForecastCache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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
