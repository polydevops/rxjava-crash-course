package com.polydevops.rxjavacrashcourse.model.weather;

/**
 * TODO: Add class header comment.
 */
public class Coord {

    private double lon;
    private double lat;

    public Coord(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLongitude() {
        return lon;
    }

    public double getLatitude() {
        return lat;
    }
}
