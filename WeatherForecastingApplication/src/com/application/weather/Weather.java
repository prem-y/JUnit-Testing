package com.application.weather;

public class Weather {
    private String city;
    private String condition;
    private double temperature;

    public Weather(String city, String condition, double temperature) {
        this.city = city;
        this.condition = condition;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public String getCondition() {
        return condition;
    }

    public double getTemperature() {
        return temperature;
    }
}
