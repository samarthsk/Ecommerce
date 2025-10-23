# Use an official OpenJDK 21 image, such as from AdoptOpenJDK or Eclipse Temurin
FROM openjdk:21-jdk-slim

# Add a volume pointing to /tmp
VOLUME /tmp

# Copy the executable JAR file into the container
COPY target/*.jar app.jar

# Expose the service port
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
