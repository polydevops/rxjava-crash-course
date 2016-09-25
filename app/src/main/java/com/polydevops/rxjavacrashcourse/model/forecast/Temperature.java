package com.polydevops.rxjavacrashcourse.model.forecast;

/**
 * TODO: Add class header comment.
 */

public class Temperature {

    private double min;
    private double max;

    private double day;
    private double night;
    private double eve;
    private double morn;

    public Temperature(double min, double max, double day, double night, double eve, double morn) {
        this.min = min;
        this.max = max;
        this.day = day;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getDay() {
        return day;
    }

    public double getNight() {
        return night;
    }

    public double getEve() {
        return eve;
    }

    public double getMorn() {
        return morn;
    }
}
