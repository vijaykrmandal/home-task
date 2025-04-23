# --- Stage 1: Build stage ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /home/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package

# --- Stage 2: Runtime stage ---
FROM gcr.io/distroless/java21-debian12
WORKDIR /app
EXPOSE 9090
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]