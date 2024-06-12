# Backend-weatherapp

Repozytorium "Backend-weatherapp" to prosty backend aplikacji weatherapp, dostępny pod adresem https://frontend-weatherapp.onrender.com. Udostępnia on dane pogodowe i oblicza generowaną energię słoneczną na podstawie czasu nasłonecznienia. Backend pobiera dane z API Open Meteo i udostępnia je w formacie JSON.

## Kod źródłowy

* **WeatherController**: Główny kontroler, który obsługuje żądania do endpointu `/weather`.
* **GlobalExceptionHandler**: Ten kod to obsługa wyjątków w aplikacji.
* **WeatherResponse**: Klasa ta zapewnia strukturę danych do przechowywania informacji o prognozie pogody oraz generowanej energii.
* **WeatherService**: Jest odpowiedzialna za pobieranie danych pogodowych z zewnętrznego API oraz obliczanie generowanej energii na podstawie tych danych.

* **calculateGeneratedEnergy(double sunshineDuration)**: Metoda obliczająca generowaną energię słoneczną na podstawie czasu nasłonecznienia(sunshineDuration). Użyłem prostego wzoru, nie daje on dokładnych wyników ale na potrzeby tego projektu zdecydowanie wystarcza.  
wygenerowana energia[kWh] =
moc instalacji[kW] x czas ekspozycji[h] x efektywność paneli


* **RestTemplate**: Klasa używana do wykonywania zapytań HTTP do API Open Meteo.

## Endpointy
`/weather`
Endpoint zwraca dane pogodowe dla podanych współrzędnych geograficznych.

## Testy

* obliczanie generowanej energii słonecznej
* obsługę nieprawidłowych współrzędnych geograficznych

## Użyte technologie

* **Java**
* **Spring Boot**
* **Maven**
* **JSON**

## Uwagi

* Wiem, że nie jest dobrą praktyką umieszczanie w repozytorium GitHobowym JAR-a ale nie potrafię jeszcze go zbudować w Dockerfile. Cały czas nad tym pracuje.
* Aby dane do apliakcji zostały załadowane trzeba chwilę poczekać.
