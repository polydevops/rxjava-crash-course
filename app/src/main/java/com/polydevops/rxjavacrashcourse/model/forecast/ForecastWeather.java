package com.polydevops.rxjavacrashcourse.model.forecast;

import com.google.gson.annotations.SerializedName;
import com.polydevops.rxjavacrashcourse.model.weather.Main;
import com.polydevops.rxjavacrashcourse.model.weather.Weather;

/**
 * TODO: Add class header comment.
 */
public class ForecastWeather {

    @SerializedName("dt")
    private long date;

    private Main main;

    private Weather weather;

    public ForecastWeather(long date, Main main, Weather weather) {
        this.date = date;
        this.main = main;
        this.weather = weather;
    }

    public long getDate() {
        return date;
    }

    public Main getMain() {
        return main;
    }

    public Weather getWeather() {
        return weather;
    }
}
