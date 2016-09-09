package com.polydevops.rxjavacrashcourse.weather;

import android.view.View;

import com.polydevops.rxjavacrashcourse.R;
import com.polydevops.rxjavacrashcourse.model.weather.WeatherResponse;
import com.polydevops.rxjavacrashcourse.router.FrontController;
import com.polydevops.rxjavacrashcourse.rx.AbstractRxPresenter;

import rx.Subscriber;
import rx.Subscription;

/**
 * Presenter for WeatherFragment
 */
public class WeatherPresenter extends AbstractRxPresenter implements WeatherContract.Presenter {

    public static final String TAG = "WeatherPresenter";

    private WeatherContract.View view;
    private WeatherContract.Interactor interactor;
    private FrontController frontController;

    public WeatherPresenter(WeatherContract.View view, WeatherContract.Interactor interactor, FrontController frontController) {
        this.view = view;
        this.interactor = interactor;
        this.frontController = frontController;
    }

    @Override
    public void onViewCreated() {
        view.getToolbar().setToolbarTitle(R.string.weather);
        getCompositeSubscription().add(view.setZipCodeTextWatcher());
    }

    @Override
    public void onCityTextChanged(String city) {
        if (city.isEmpty()) {
            view.setGetWeatherButtonVisibility(View.GONE);
            clearWeatherData();
        } else {
            view.setGetWeatherButtonVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getWeather(String city) {
        final Subscription s = interactor.getWeather(city)
                .subscribe(new Subscriber<WeatherResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(WeatherResponse weather) {
                        if (weather != null) {
                            setWeatherData(weather);
                        } else {
                            setNoWeatherData();
                        }
                    }
                });

        getCompositeSubscription().add(s);
    }

    private void setWeatherData(final WeatherResponse weather) {
        view.setLocation(weather.getName());
        view.setTemperature(weather.getMain().getTemp() + "°");
        view.setDescription(weather.getWeather().get(0).getDescription());
        view.setHumidity(weather.getMain().getHumidity() + "% Humidity");
    }

    private void clearWeatherData() {
        view.setLocation("");
        view.setTemperature("");
        view.setDescription("");
        view.setHumidity("");
    }

    private void setNoWeatherData() {
        view.setLocation("City does not exist.");
    }
}
