package com.example.backendweatherapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class WeatherController {

    @GetMapping("/weather")
    public String getWeather(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) {

        if (latitude == null || longitude == null) {
            return "Latitude and longitude parameters are required";
        }
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            return "Latitude must be between -90 and 90, and longitude must be between -180 and 180";
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&past_days=7&&hourly=&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration&timezone=GMT";
            String weatherData = restTemplate.getForObject(apiUrl, String.class);

            JSONObject jsonObject = new JSONObject(weatherData);
            JSONObject dailyData = jsonObject.getJSONObject("daily");
            JSONArray dates = dailyData.getJSONArray("time");
            JSONArray sunshineDurations = dailyData.getJSONArray("sunshine_duration");
            JSONArray minTemperature = dailyData.getJSONArray("temperature_2m_min");
            JSONArray maxTemperature = dailyData.getJSONArray("temperature_2m_max");
            JSONArray weatherCodes = dailyData.getJSONArray("weather_code");

            List<JSONObject> results = new ArrayList<>();

            int startIndex = Math.max(0, dates.length() - 7);

            for (int i = startIndex; i < dates.length(); i++) {
                JSONObject result = new JSONObject();
                double sunshineDuration = sunshineDurations.getDouble(i);
                double generatedEnergy = calculateGeneratedEnergy(sunshineDuration);

                double minTemp = minTemperature.getDouble(i);
                double maxTemp = maxTemperature.getDouble(i);

                result.put("date", dates.getString(i));
                result.put("min_temperature_C", minTemp);
                result.put("max_temperature_C", maxTemp);
                result.put("generated_energy_kWh", generatedEnergy);
                result.put("weather_code", weatherCodes.getInt(i));

                results.add(result);
            }

            JSONArray jsonResults = new JSONArray(results);

            return jsonResults.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while fetching weather data";
        }
    }

    public double calculateGeneratedEnergy(double sunshineDuration) {
        double installedPower = 2.5; // Moc instalacji fotowoltaicznej w kW
        double panelEfficiency = 0.2; // Efektywność paneli
        double sunshineDurationHours = sunshineDuration / 3600; // Przeliczenie czasu ekspozycji z sekund na godziny
        return installedPower * sunshineDurationHours * panelEfficiency;
    }
}


