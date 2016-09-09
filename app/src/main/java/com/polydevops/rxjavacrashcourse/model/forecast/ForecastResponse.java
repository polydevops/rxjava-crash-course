package com.polydevops.rxjavacrashcourse.model.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * TODO: Add class header comment.
 */
public class ForecastResponse {

    @SerializedName("city")
    private City location;

    @SerializedName("list")
    private List<ForecastWeather> forecast;

    private int cod;

    public ForecastResponse(City location, List<ForecastWeather> forecast) {
        this.location = location;
        this.forecast = forecast;
    }

    public City getLocation() {
        return location;
    }

    public List<ForecastWeather> getForecast() {
        return forecast;
    }

    public int getCode() {
        return cod;
    }
}
