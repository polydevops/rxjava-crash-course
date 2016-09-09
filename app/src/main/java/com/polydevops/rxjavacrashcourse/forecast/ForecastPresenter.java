package com.polydevops.rxjavacrashcourse.forecast;

import android.support.annotation.NonNull;

import com.polydevops.rxjavacrashcourse.R;
import com.polydevops.rxjavacrashcourse.model.forecast.ForecastResponse;
import com.polydevops.rxjavacrashcourse.model.forecast.ForecastWeather;
import com.polydevops.rxjavacrashcourse.router.FrontController;
import com.polydevops.rxjavacrashcourse.rx.AbstractRxPresenter;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * TODO: Add class header comment.
 */
public class ForecastPresenter extends AbstractRxPresenter implements ForecastContract.Presenter {

    private ForecastContract.View view;
    private ForecastContract.Interactor interactor;
    private FrontController frontController;

    public ForecastPresenter(final ForecastContract.View view, final ForecastContract.Interactor interactor, final FrontController frontController) {
        this.view = view;
        this.interactor = interactor;
        this.frontController = frontController;
    }

    @Override
    public void onViewCreated() {
        view.getToolbar().setToolbarTitle(R.string.forecast);

        final String city = interactor.getSavedForecastCity();
        if (city != null) {
            getForecastDataForCity(city);
        } else {
            view.setForecastRecyclerAdapter(new ForecastAdapter());
        }
    }

    @Override
    public void onActionSetForecastCityClicked() {
        final String city = interactor.getSavedForecastCity();
        view.displaySetForecastCityDialog(city);
    }

    @Override
    public void onForecastCitySet(String city) {
        interactor.setSavedForecastCity(city);
        getForecastDataForCity(city);
    }

    private void getForecastDataForCity(@NonNull final String city) {
        final Subscription subscription = interactor.getWeatherForecast(city)
                .subscribe(new Subscriber<ForecastResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ForecastResponse forecastResponse) {
                        if (forecastResponse != null && forecastResponse.getCode() == 200) {
                            final List<ForecastWeather> forecast = forecastResponse.getForecast();
                            final ForecastAdapter adapter = new ForecastAdapter(forecast);
                            view.setForecastRecyclerAdapter(adapter);
                        } else {
                            view.setForecastRecyclerAdapter(new ForecastAdapter());
                        }
                    }
                });

        getCompositeSubscription().add(subscription);
    }

}
