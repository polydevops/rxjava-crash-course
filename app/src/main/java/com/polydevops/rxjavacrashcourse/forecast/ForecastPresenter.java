package com.polydevops.rxjavacrashcourse.forecast;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;

import com.polydevops.rxjavacrashcourse.R;
import com.polydevops.rxjavacrashcourse.model.forecast.ForecastResponse;
import com.polydevops.rxjavacrashcourse.model.forecast.ForecastWeather;
import com.polydevops.rxjavacrashcourse.router.FrontController;
import com.polydevops.rxjavacrashcourse.rx.AbstractRxPresenter;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

import static com.polydevops.rxjavacrashcourse.R.string.forecast;

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
        view.getToolbar().setToolbarTitle(forecast);

        final String city = interactor.getSavedForecastCity();

        if (city != null) {
            view.setForecastLocation(city);
            getForecastDataForCity(city);
        } else {
            view.setForecastRecyclerAdapter(new ForecastAdapter());
            view.displaySetForecastCityDialog("");
        }
    }

    @Override
    public void onBackPressed(FragmentActivity activity) {
        frontController.goBack(activity);
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
        final Subscription subscription = interactor.getCachedWeatherForecast()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        view.displayLoadingDialog(R.string.retrieving_forecast);
                    }
                })
                .flatMap(new Func1<List<ForecastWeather>, Observable<List<ForecastWeather>>>() {
                    @Override
                    public Observable<List<ForecastWeather>> call(List<ForecastWeather> forecast) {
                        if (forecast != null && !forecast.isEmpty()) {
                            return Observable.just(forecast);
                        } else {
                            return getCurrentWeatherFromService(city);
                        }
                    }
                })
                .subscribe(new Subscriber<List<ForecastWeather>>() {
                    @Override
                    public void onCompleted() {
                        view.dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.dismissLoadingDialog();
                    }

                    @Override
                    public void onNext(List<ForecastWeather> forecast) {
                        if (forecast != null) {
                            final ForecastAdapter adapter = new ForecastAdapter(forecast);
                            view.setForecastRecyclerAdapter(adapter);
                            cacheForecastData(forecast);
                        }
                    }
                });

        getCompositeSubscription().add(subscription);
    }

    private void refreshForecastData(final String city) {
        final Subscription subscription = interactor.getCurrentWeatherForecast(city)
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

                            cacheForecastData(forecastResponse);
                        } else {
                            view.setForecastRecyclerAdapter(new ForecastAdapter());
                        }
                    }
                });

        getCompositeSubscription().add(subscription);
    }

    private Observable<>
    private void cacheForecastData(final List<ForecastWeather> forecast) {
        final Subscription subscription = interactor.deleteForecastCache()
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        return interactor.saveWeatherForecast(forecast);
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.displaySnackbar(R.string.forecast_not_refreshed, Snackbar.LENGTH_LONG);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        if (integer > 0) {
                            view.displaySnackbar(R.string.forecast_refreshed, Snackbar.LENGTH_LONG);
                        } else {
                            view.displaySnackbar(R.string.forecast_not_refreshed, Snackbar.LENGTH_LONG);
                        }
                    }
                });

        getCompositeSubscription().add(subscription);
    }

}
