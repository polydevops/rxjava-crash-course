package com.polydevops.rxjavacrashcourse.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polydevops.rxjavacrashcourse.R;
import com.polydevops.rxjavacrashcourse.RxJavaCrashCourseApplication;
import com.polydevops.rxjavacrashcourse.forecast.ForecastFragment;
import com.polydevops.rxjavacrashcourse.router.FrontController;
import com.polydevops.rxjavacrashcourse.view.IToolbar;
import com.polydevops.rxjavacrashcourse.weather.WeatherFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private IToolbar toolbar;
    private FrontController frontController;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frontController = RxJavaCrashCourseApplication.getFrontController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setToolbarTitle(R.string.app_name);
    }

    @OnClick(R.id.btn_weather_example)
    protected void onWeatherExampleButtonPressed() {
        frontController
                .showFragment(getActivity(), WeatherFragment.TAG, null, R.id.content_frame);
    }

    @OnClick(R.id.btn_forecast_example)
    protected void onForecastExampleButtonPressed() {
        frontController
                .showFragment(getActivity(), ForecastFragment.TAG, null, R.id.content_frame);
    }

}
