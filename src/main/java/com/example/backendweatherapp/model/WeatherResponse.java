package com.example.backendweatherapp.model;
public class WeatherResponse {
    private String date;
    private double minTemperature;
    private double maxTemperature;
    private double generatedEnergy;
    private int weatherCode;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getGeneratedEnergy() {
        return generatedEnergy;
    }

    public void setGeneratedEnergy(double generatedEnergy) {
        this.generatedEnergy = generatedEnergy;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }
}
