# Backend-weatherapp

Repozytorium "Backend-weatherapp" to prosty backend aplikacji weatherapp, dostępny pod adresem https://frontend-weatherapp.onrender.com. Udostępnia dane pogodowe i oblicza generowaną energię słoneczną na podstawie czasu nasłonecznienia. Serwer pobiera dane z API Open Meteo i udostępnia je w formacie JSON.

## Kod źródłowy

* **WeatherController**: Główny kontroler, który obsługuje żądania do endpointu `/weather`.
* **calculateGeneratedEnergy(double sunshineDuration)**: Metoda obliczająca generowaną energię słoneczną na podstawie czasu nasłonecznienia(sunshineDuration).
* **RestTemplate**: Klasa używana do wykonywania zapytań HTTP do API Open Meteo.

## Endpointy
`/weather`
Endpoint zwraca dane pogodowe dla podanych współrzędnych geograficznych.

## Użyte technologie

* **Java**
* **Spring Boot**
* **Maven**
* **JSON**
