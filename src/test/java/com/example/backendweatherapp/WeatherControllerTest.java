package com.example.backendweatherapp;


import com.example.backendweatherapp.controller.WeatherController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherControllerTest {

    @Test
    public void testCalculateGeneratedEnergy() {
        WeatherController controller = new WeatherController();

        // Test kiedy ustawiamy susnshineDuration na 0
        double result1 = controller.calculateGeneratedEnergy(0);
        // Wynik powienien byc 0 kiedy sunshineDuration = 0
        // installedPower * sunshineDuration * panelEfficiency
        assertEquals(0, result1);

        // Test kiedy ustawiamy susnhineduration na 1 godzinę czyli 3600 sek.
        double result2 = controller.calculateGeneratedEnergy(3600);
        // Zakładając installedPower = 2.5 kW, panelEfficiency = 0.2, susnhineDuration = 3600, wynik powinien wynosić 0.5 kWh
        assertEquals(0.5, result2);
    }
    @Test
    public void testGetWeatherWithInvalidLatitudeAndLongitude() {
        WeatherController controller = new WeatherController();
        String result = controller.getWeather(200.0, 300.0);
        assertEquals("Latitude must be between -90 and 90, and longitude must be between -180 and 180", result);
    }
    @Test
    public void testGetWeatherWithNullCoordinates() {
        WeatherController controller = new WeatherController();
        String result = controller.getWeather(null, null);
        assertEquals("Latitude and longitude parameters are required", result);
    }
    @Test
    public void testGetWeatherWithNullLatitude() {
        WeatherController controller = new WeatherController();
        String result = controller.getWeather(null, 19.343);
        assertEquals("Latitude and longitude parameters are required", result);
    }
    @Test
    public void testGetWeatherWithNullLongitude() {
        WeatherController controller = new WeatherController();
        String result = controller.getWeather(53.545, null);
        assertEquals("Latitude and longitude parameters are required", result);
    }
    @Test
    public void testGetWeatherDataJSONCompleteness() throws JSONException {
        WeatherController controller = new WeatherController();
        String result = controller.getWeather(54.543, -82.4256);

        JSONArray jsonResult = new JSONArray(result);

        // Tu sprawdzamy czy istnieje przynajmije jeden wpis w tablicy JSONArray
        assertTrue(jsonResult.length() > 0);

        // Sprawdzamy, czy wszystkie wpisy zawieraja oczekiwane klucze
        for (int i = 0; i < jsonResult.length(); i++) {
            JSONObject entry = jsonResult.getJSONObject(i);
            assertTrue(entry.has("date"));
            assertTrue(entry.has("min_temperature_C"));
            assertTrue(entry.has("max_temperature_C"));
            assertTrue(entry.has("generated_energy_kWh"));
            assertTrue(entry.has("weather_code"));
        }
    }
}