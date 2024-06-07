FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/weather-app-0.0.1-SNAPSHOT.jar /app/weather-app.jar

CMD ["java", "-jar", "weather-app.jar"]

