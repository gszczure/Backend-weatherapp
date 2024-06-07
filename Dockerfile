# Użyj obrazu Javy jako obrazu bazowego
FROM bellsoft/liberica-openjdk-alpine:17

# Ustaw katalog roboczy w kontenerze
WORKDIR /app

# Skopiuj plik mvnw, plik pom.xml oraz katalog src do katalogu roboczego
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn ./.mvn
COPY pom.xml .
COPY src ./src

# Pobierz zależności i zbuduj aplikację
RUN ./mvnw package -DskipTests

# Ustal zmienną środowiskową dla pliku jar
ENV JAR_FILE=target/weather-app-0.0.1-SNAPSHOT.jar

# Skopiuj zbudowany plik jar do katalogu roboczego
COPY ${JAR_FILE} app.jar

# Określ komendę startową kontenera
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
