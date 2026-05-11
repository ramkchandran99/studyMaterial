# Build stage - using Maven with Eclipse Temurin 17
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage - choose ONE of these options:
# Option A: Standard JRE (recommended)
FROM eclipse-temurin:17-jre

# Option B: Alpine (smaller image)
# FROM eclipse-temurin:17-jre-alpine

# Create a non-root user for security
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Copy the built JAR
COPY --from=build /app/target/*.jar app.jar

EXPOSE 7777
ENTRYPOINT ["java", "-jar", "/app.jar"]
