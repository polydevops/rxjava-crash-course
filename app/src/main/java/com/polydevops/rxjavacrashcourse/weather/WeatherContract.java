package com.polydevops.rxjavacrashcourse.weather;

import android.support.v4.app.FragmentActivity;

import com.polydevops.rxjavacrashcourse.model.weather.WeatherResponse;
import com.polydevops.rxjavacrashcourse.rx.IRxPresenter;
import com.polydevops.rxjavacrashcourse.view.IToolbar;

import rx.Observable;
import rx.Subscription;

/**
 * Defines the contract for the weather screen
 */
public interface WeatherContract {

    interface View {

        IToolbar getToolbar();

        // setup methods
        Subscription setZipCodeTextWatcher();

        // UI setters
        void setLocation(final String location);
        void setTemperature(final String temperature);
        void setDescription(final String description);
        void setHumidity(final String humidity);
        void setGetWeatherButtonVisibility(int visibility);
    }

    interface Presenter extends IRxPresenter {
        void onViewCreated();
        void onBackPressed(FragmentActivity activity);

        void onCityTextChanged(final String zipCode);
        void getWeather(final String zipCode);
    }

    interface Interactor {
        Observable<WeatherResponse> getWeather(final String zipCode);
    }
}
