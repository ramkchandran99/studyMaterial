FROM eclipse-temurin:17-jre-jammy

# Copy built jar from target (Maven) or build/libs (Gradle)
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/app.jar

# Expose application port
EXPOSE 7777

# Run the jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
