package com.application.weather;

import java.util.HashMap;
import java.util.Map;

public class WeatherService {
    private Map<String, Weather> weatherData;

    public WeatherService() {
        weatherData = new HashMap<>();
    }

    public void addWeatherData(String city, Weather weather) {
        weatherData.put(city, weather);
    }

    public Weather getWeather(String city) {
        return weatherData.get(city);
    }

    public void updateWeatherData(String city, Weather weather) {
        if (weatherData.containsKey(city)) {
            weatherData.put(city, weather);
        } else {
            throw new IllegalArgumentException("No weather data for city: " + city);
        }
    }

    public void removeWeatherData(String city) {
        weatherData.remove(city);
    }
}
