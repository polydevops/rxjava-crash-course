package com.polydevops.rxjavacrashcourse.forecast;

import com.polydevops.rxjavacrashcourse.model.forecast.ForecastResponse;
import com.polydevops.rxjavacrashcourse.rx.IRxPresenter;
import com.polydevops.rxjavacrashcourse.view.IToolbar;

import rx.Observable;

/**
 * TODO: Add class header comment.
 */
public interface ForecastContract {

    interface View {
        IToolbar getToolbar();

        void setForecastRecyclerAdapter(ForecastAdapter adapter);
        void displaySetForecastCityDialog(String city);
    }

    interface Presenter extends IRxPresenter {
        void onViewCreated();

        void onActionSetForecastCityClicked();
        void onForecastCitySet(String city);
    }

    interface Interactor {
        Observable<ForecastResponse> getWeatherForecast(String city);
        Observable<Integer> saveWeatherForecast(ForecastResponse forecast);
        String getSavedForecastCity();
        void setSavedForecastCity(String city);
    }
}
