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