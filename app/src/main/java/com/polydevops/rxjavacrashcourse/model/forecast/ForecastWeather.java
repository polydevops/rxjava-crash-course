package com.polydevops.rxjavacrashcourse.model.forecast;

import com.google.gson.annotations.SerializedName;
import com.polydevops.rxjavacrashcourse.model.weather.Weather;

import java.util.List;

/**
 * TODO: Add class header comment.
 */
public class ForecastWeather {

    @SerializedName("dt")
    private long date;

    private Temperature temp;

    private double pressure;

    private int humidity;

    private List<Weather> weather;

    public ForecastWeather(long date, Temperature temp, double pressure, int humidity, List<Weather> weather) {
        this.date = date;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
    }

    public long getDate() {
        return date;
    }

    public Temperature getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public Weather getWeather() {
        return (weather != null) ? weather.get(0) : null;
    }
}
