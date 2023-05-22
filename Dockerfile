FROM gradle:7.3.3-jdk11 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon
FROM openjdk:18
WORKDIR /app
COPY build/libs/dataImport-0.0.1-SNAPSHOT.jar dataImport.jar
EXPOSE 8080
CMD ["java", "-jar", "dataImport.jar"]

