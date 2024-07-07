package com.application.weather.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.application.weather.Weather;
import com.application.weather.WeatherService;

class WeatherServiceTest {
    private WeatherService weatherService;
    private Weather weather1;
    private Weather weather2;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherService();
        weather1 = new Weather("New York", "Sunny", 25.0);
        weather2 = new Weather("Los Angeles", "Cloudy", 22.0);
        weatherService.addWeatherData("New York", weather1);
        weatherService.addWeatherData("Los Angeles", weather2);
    }

    @Test
    void testAddWeatherData() {
        Weather weather3 = new Weather("Chicago", "Rainy", 18.0);
        weatherService.addWeatherData("Chicago", weather3);
        assertEquals(weather3, weatherService.getWeather("Chicago"));
    }

    @Test
    void testGetWeather() {
        assertEquals(weather1, weatherService.getWeather("New York"));
        assertEquals(weather2, weatherService.getWeather("Los Angeles"));
    }

    @Test
    void testUpdateWeatherData() {
        Weather updatedWeather = new Weather("New York", "Stormy", 20.0);
        weatherService.updateWeatherData("New York", updatedWeather);
        assertEquals(updatedWeather, weatherService.getWeather("New York"));
    }

    @Test
    void testUpdateWeatherDataForNonexistentCity() {
        Weather updatedWeather = new Weather("Boston", "Sunny", 25.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            weatherService.updateWeatherData("Boston", updatedWeather);
        });
        assertEquals("No weather data for city: Boston", exception.getMessage());
    }

    @Test
    void testRemoveWeatherData() {
        weatherService.removeWeatherData("New York");
        assertNull(weatherService.getWeather("New York"));
    }
}
