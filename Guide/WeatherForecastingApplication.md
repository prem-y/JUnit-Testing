# Weather Forecasting Application

### Step 1: Set Up Your Project

1. **Create a new Java Project**:
   - Open Eclipse.
   - Go to `File -> New -> Java Project`.
   - Enter the project name (`WeatherForecastingApplication`).

2. **Add JUnit to your Project**:
   - Right-click on your project in the Project Explorer.
   - Select `Build Path -> Add Libraries...`.
   - Choose `JUnit` and click `Next`.
   - Select `JUnit 5` and click `Finish`.

### Step 2: Create Your Weather Forecasting Classes

1. **Create a package**:
   - Right-click on `src`.
   - Select `New -> Package`.
   - Enter a package name (`com.application.weather`).

2. **Create a `Weather` class**:
   - Right-click on the package you just created.
   - Select `New -> Class`.
   - Enter the class name (`Weather`).

```java
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
```

3. **Create a `WeatherService` class**:
   - Right-click on the package you just created.
   - Select `New -> Class`.
   - Enter the class name (`WeatherService`).

```java
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
```

### Step 3: Create Your JUnit Test Classes

1. **Create a test package**:
   - Right-click on `src`.
   - Select `New -> Package`.
   - Enter the test package name (`com.application.weather.test`).

2. **Create a `WeatherTest` class**:
   - Right-click on the test package.
   - Select `New -> JUnit Test Case`.
   - Enter the test class name (`WeatherTest`).
   - Ensure `JUnit 5` is selected and click `Finish`.

3. **Write JUnit tests for `Weather` class**:

```java
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
```

4. **Create a `WeatherServiceTest` class**:
   - Right-click on the test package.
   - Select `New -> JUnit Test Case`.
   - Enter the test class name (`WeatherServiceTest`).
   - Ensure `JUnit 5` is selected and click `Finish`.

5. **Write JUnit tests for `WeatherService` class**:

```java
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
```

### Step 4: Run Your Tests

1. **Run the tests**:
   - Right-click on the `WeatherTest` class.
   - Select `Run As -> JUnit Test`.
   - Repeat the same steps for `WeatherServiceTest` class.

### Explanation

- **Weather Class**: This class represents weather data with properties like city, condition, and temperature.
- **WeatherService Class**: This class manages weather data for multiple cities. It includes methods to add, get, update, and remove weather data.
- **WeatherTest Class**: This is your JUnit test class for the `Weather` class. It contains various test methods to verify the behavior of the `Weather` class.
- **WeatherServiceTest Class**: This is your JUnit test class for the `WeatherService` class. It contains various test methods to verify the behavior of the `WeatherService` class.
