package com.example.backendweatherapp;

import com.example.backendweatherapp.controller.WeatherController;
import com.example.backendweatherapp.model.WeatherResponse;
import com.example.backendweatherapp.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWeatherWithValidParameters() throws Exception {
        double latitude = 40.0;
        double longitude = -75.0;
        List<WeatherResponse> expectedResponse = Collections.singletonList(new WeatherResponse());
        when(weatherService.getWeatherData(latitude, longitude)).thenReturn(expectedResponse);
        ResponseEntity<List<WeatherResponse>> response = weatherController.getWeather(latitude, longitude);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
    @Test
    public void testGetWeatherWithOneNullParameters() throws Exception {
        Double latitude = null;
        Double longitude = -75.0;
        ResponseEntity<List<WeatherResponse>> response = weatherController.getWeather(latitude, longitude);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    public void testGetWeatherWithAllNullParameters() throws Exception {
        Double latitude = null;
        Double longitude = null;
        ResponseEntity<List<WeatherResponse>> response = weatherController.getWeather(latitude, longitude);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    // Wartości testowe
    private static final double INSTALLED_POWER = 2.5; // kW
    private static final double PANEL_EFFICIENCY = 0.2;
    @Test
    public void testCalculateGeneratedEnergy() {
        WeatherService weatherService = new WeatherService();
        // Test kiedy ustawiamy sunshineDuration na 0
        double result1 = weatherService.calculateGeneratedEnergy(0);
        // Oczekiwany wynik to 0, gdy sunshineDuration = 0
        // installedPower * sunshineDuration * panelEfficiency
        assertEquals(0, result1);
        // Test kiedy ustawiamy sunshineDuration na 1 godzinę, czyli 3600 sekund
        double result2 = weatherService.calculateGeneratedEnergy(3600);
        // Zakładając installedPower = 2.5 kW, panelEfficiency = 0.2, sunshineDuration = 3600 sekund
        // Wynik powinien wynosić 0.5 kWh
        assertEquals(0.5, result2);
    }
}
