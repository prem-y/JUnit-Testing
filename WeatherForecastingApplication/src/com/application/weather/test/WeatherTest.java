package com.application.weather.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.application.weather.Weather;

class WeatherTest {
    private Weather weather;

    @BeforeEach
    void setUp() {
        weather = new Weather("New York", "Sunny", 25.0);
    }

    @Test
    void testCity() {
        assertEquals("New York", weather.getCity());
    }

    @Test
    void testCondition() {
        assertEquals("Sunny", weather.getCondition());
    }

    @Test
    void testTemperature() {
        assertEquals(25.0, weather.getTemperature());
    }
}