package com.polydevops.rxjavacrashcourse.forecast;

import android.support.v4.app.FragmentActivity;

import com.polydevops.rxjavacrashcourse.model.forecast.ForecastWeather;
import com.polydevops.rxjavacrashcourse.rx.IRxPresenter;
import com.polydevops.rxjavacrashcourse.view.IToolbar;

import java.util.List;

import rx.Observable;

/**
 * The contract for the forecast screen view, presenter, and interactor
 */
public interface ForecastContract {

    interface View {
        IToolbar getToolbar();
        String getString(int stringResId);

        void setForecastRecyclerAdapter(ForecastAdapter adapter);
        void setForecastLocation(final String location);

        void displayLoadingDialog(int loadingMessageStringResId);
        void dismissLoadingDialog();

        void displaySetForecastCityDialog(String city);
        void displaySnackbar(int stringResId, int duration);
    }

    interface Presenter extends IRxPresenter {
        void onViewCreated();
        void onBackPressed(FragmentActivity activity);

        void onActionSetForecastCityClicked();
        void onForecastCitySet(String city);
    }

    interface Interactor {
        Observable<List<ForecastWeather>> getCurrentForecast(String city);

        Observable<Integer> saveForecastCache(List<ForecastWeather> forecast);
        Observable<List<ForecastWeather>> getForecastCache();
        Observable<Integer> deleteForecastCache();

        String getSavedForecastCity();
        void setSavedForecastCity(String city);
    }
}
