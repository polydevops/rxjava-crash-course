package com.polydevops.rxjavacrashcourse.weather;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;
import com.polydevops.rxjavacrashcourse.R;
import com.polydevops.rxjavacrashcourse.RxJavaCrashCourseApplication;
import com.polydevops.rxjavacrashcourse.router.FrontController;
import com.polydevops.rxjavacrashcourse.view.IOnBackPressed;
import com.polydevops.rxjavacrashcourse.view.IToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Displays the weather
 */
public class WeatherFragment extends Fragment implements WeatherContract.View, IOnBackPressed {

    public static final String TAG = "WeatherFragment";

    // region ButterKnife Binds
    @BindView(R.id.edit_city)
    EditText bEditZipCode;

    @BindView(R.id.btn_get_weather)
    Button bBtnGetWeather;

    @BindView(R.id.text_weather_location)
    TextView bTextLocation;

    @BindView(R.id.text_weather_temperature)
    TextView bTextTemperature;

    @BindView(R.id.text_weather_description)
    TextView bTextDescription;

    @BindView(R.id.text_weather_humidity)
    TextView bTextHumidity;

    private IToolbar toolbar;

    private WeatherContract.Presenter presenter;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            toolbar = (IToolbar) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Please have activity implement IToolbar.");
        }
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final WeatherContract.View view = this;
        final WeatherContract.Interactor interactor = new WeatherInteractor(RxJavaCrashCourseApplication.getWebServiceManager());
        final FrontController frontController = RxJavaCrashCourseApplication.getFrontController();
        presenter = new WeatherPresenter(view, interactor, frontController);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated();
    }

    @Override
    public IToolbar getToolbar() {
        return toolbar;
    }

    @Override
    public Subscription setZipCodeTextWatcher() {
        return RxTextView.afterTextChangeEvents(bEditZipCode)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
                        presenter.onCityTextChanged(textViewAfterTextChangeEvent.view().getText().toString());
                    }
                });
    }

    @Override
    public void setLocation(String location) {
        bTextLocation.setText(location);
    }

    @Override
    public void setTemperature(String temperature) {
        bTextTemperature.setText(temperature);
    }

    @Override
    public void setDescription(String description) {
        bTextDescription.setText(description);
    }

    @Override
    public void setHumidity(String humidity) {
        bTextHumidity.setText(humidity);
    }

    @Override
    public void setGetWeatherButtonVisibility(int visibility) {
        bBtnGetWeather.setVisibility(visibility);
    }

    @OnClick(R.id.btn_get_weather)
    protected void onGetWeatherButtonClicked() {
        presenter.getWeather(bEditZipCode.getText().toString());
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed(getActivity());
    }
}
