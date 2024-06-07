package com.example.backendweatherapp.service;

import com.example.backendweatherapp.model.WeatherResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private static final String API_URL_TEMPLATE = "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&past_days=7&hourly=&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration&timezone=GMT";
    private static final double INSTALLED_POWER = 2.5; // kW
    private static final double PANEL_EFFICIENCY = 0.2;

    public List<WeatherResponse> getWeatherData(double latitude, double longitude) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = String.format(API_URL_TEMPLATE, latitude, longitude);
        String weatherData = restTemplate.getForObject(apiUrl, String.class);

        JSONObject jsonObject = new JSONObject(weatherData);
        JSONObject dailyData = jsonObject.getJSONObject("daily");
        JSONArray dates = dailyData.getJSONArray("time");
        JSONArray sunshineDurations = dailyData.getJSONArray("sunshine_duration");
        JSONArray minTemperature = dailyData.getJSONArray("temperature_2m_min");
        JSONArray maxTemperature = dailyData.getJSONArray("temperature_2m_max");
        JSONArray weatherCodes = dailyData.getJSONArray("weather_code");

        List<WeatherResponse> results = new ArrayList<>();
        int startIndex = Math.max(0, dates.length() - 7);

        for (int i = startIndex; i < dates.length(); i++) {
            WeatherResponse response = new WeatherResponse();
            response.setDate(dates.getString(i));
            response.setMinTemperature(minTemperature.getDouble(i));
            response.setMaxTemperature(maxTemperature.getDouble(i));
            response.setWeatherCode(weatherCodes.getInt(i));
            response.setGeneratedEnergy(calculateGeneratedEnergy(sunshineDurations.getDouble(i)));
            results.add(response);
        }

        return results;
    }

    public double calculateGeneratedEnergy(double sunshineDuration) {
        double sunshineDurationHours = sunshineDuration / 3600; // seconds to hours
        return INSTALLED_POWER * sunshineDurationHours * PANEL_EFFICIENCY;
    }
}
