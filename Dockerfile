FROM gradle:7.6.1-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon
FROM openjdk:18
WORKDIR /app
COPY --from=build build/libs/dataImport-0.0.1-SNAPSHOT.jar dataImport.jar
EXPOSE 8080
CMD ["java", "-jar", "dataImport.jar"]

