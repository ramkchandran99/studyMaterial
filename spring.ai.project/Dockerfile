# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy maven configuration first (for dependency caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-jammy

# Copy the JAR from the builder stage
COPY --from=builder /app/target/*.jar /app/app.jar

# Expose your application port
EXPOSE 7777

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
