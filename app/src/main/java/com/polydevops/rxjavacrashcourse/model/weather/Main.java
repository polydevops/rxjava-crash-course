package com.polydevops.rxjavacrashcourse.model.weather;

/**
 * TODO: Add class header comment.
 */
public class Main {

    private double temp;
    private int pressure;
    private int humidity;
    private double tempMin;
    private double tempMax;

    public Main(double temp, int pressure, int humidity, double tempMin, double tempMax) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public double getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }
}
