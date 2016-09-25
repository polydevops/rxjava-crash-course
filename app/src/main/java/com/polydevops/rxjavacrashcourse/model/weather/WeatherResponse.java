package com.polydevops.rxjavacrashcourse.model.weather;

import java.util.List;

/**
 * Model to represent the weather retrieved from openweathermap.org
 */
public class WeatherResponse {

    private String name;
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int cod;

    public WeatherResponse(String name, Coord coord, List<Weather> weather, String base, Main main, int cod) {
        this.name = name;
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public int getCode() {
        return cod;
    }
}
