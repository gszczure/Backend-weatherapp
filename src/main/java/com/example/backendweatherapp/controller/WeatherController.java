package com.example.backendweatherapp.controller;

import com.example.backendweatherapp.model.WeatherResponse;
import com.example.backendweatherapp.service.WeatherService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin
@RestController
@Validated
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<List<WeatherResponse>> getWeather(
            @RequestParam("latitude") @NotNull @Min(-90) @Max(90) Double latitude,
            @RequestParam("longitude") @NotNull @Min(-180) @Max(180) Double longitude) throws JSONException {

        List<WeatherResponse> weatherData = weatherService.getWeatherData(latitude, longitude);
        return ResponseEntity.ok(weatherData);
    }
}
