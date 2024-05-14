# Użyj oficjalnego obrazu Java jako bazowego obrazu
FROM openjdk:17-jdk-alpine

# Ustawiamy katalog roboczy wewnątrz kontenera
WORKDIR /app

# Skopiuj plik JAR do kontenera
COPY target/weather-app-0.0.1-SNAPSHOT.jar /app/weather-app.jar

# Uruchomienie aplikacji Spring Boot po uruchomieniu kontenera
CMD ["java", "-jar", "weather-app.jar"]
