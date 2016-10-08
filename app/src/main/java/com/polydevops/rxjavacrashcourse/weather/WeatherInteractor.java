package com.polydevops.rxjavacrashcourse.weather;

import com.polydevops.rxjavacrashcourse.model.weather.WeatherResponse;
import com.polydevops.rxjavacrashcourse.webService.WebServiceManager;

import rx.Observable;

/**
 * Provides methods for the weather API
 */
public class WeatherInteractor implements WeatherContract.Interactor {

    private WebServiceManager webServiceManager;

    public WeatherInteractor(WebServiceManager webServiceManager) {
        this.webServiceManager = webServiceManager;
    }

    @Override
    public Observable<WeatherResponse> getWeather(String zipCode) {
        return webServiceManager
                .getWeather(zipCode);
    }
}
