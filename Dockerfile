# Do poprawy
FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /app

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn ./.mvn
COPY pom.xml .
COPY src ./src