# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-17 AS builder

# Set working directory to your project folder
WORKDIR /app

# Copy Maven configuration from the subdirectory
COPY spring.ai.project/pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code from the subdirectory
COPY spring.ai.project/src ./src

# Build your application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-jammy

# Copy the built JAR
COPY --from=builder /app/target/*.jar /app/app.jar

EXPOSE 7777
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
