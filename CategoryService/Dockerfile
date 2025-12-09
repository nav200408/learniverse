# Use OpenJDK 8 Alpine Linux as the base image
FROM openjdk:8-jdk-alpine
# Copy the built JAR file from the local target directory into the Docker image
# Note: The JAR file name should match your actual build output
COPY target/CategoryService-0.0.1-SNAPSHOT.jar CategoryService-0.0.1-SNAPSHOT.jar
# Specify the command to run when the container starts
ENTRYPOINT ["java", "-jar", "/configservice-0.0.1-SNAPSHOT.jar"]