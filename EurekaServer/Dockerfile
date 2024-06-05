# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/local/openjdk-17

RUN apt-get update && apt-get install -y dos2unix curl

# Copy the Gradle wrapper and necessary files
COPY gradlew /app/gradlew
COPY gradle /app/gradle
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

RUN dos2unix /app/gradlew

RUN chmod +x /app/gradlew

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Copy the source code
COPY src /app/src

# Build the application
RUN ./gradlew clean build -x test

# Copy the executable jar file into the container
RUN cp $(find build/libs -name "*.jar" | head -n 1) /app/eureka-server.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/eureka-server.jar"]