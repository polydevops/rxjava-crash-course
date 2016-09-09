package com.polydevops.rxjavacrashcourse.model.forecast;

import com.polydevops.rxjavacrashcourse.model.weather.Coord;

/**
 * TODO: Add class header comment.
 */
public class City {

    private long id;
    private String name;
    private Coord coord;
    private String country;

    public City(long id, String name, Coord coord, String country) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }
}
