# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/local/openjdk-17
RUN export JAVA_HOME

# Copy the Gradle wrapper and the build.gradle file
COPY gradlew /app/gradlew
COPY gradle /app/gradle
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

# Copy the source code
COPY src /app/src

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew clean build -x test

# Copy the executable jar file into the container
RUN cp $(find build/libs -name "*.jar" | head -n 1) /app/daily-usage-service.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/daily-usage-service.jar"]