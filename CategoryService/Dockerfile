# 1. Change version from 8 to 17 to match your compiled JAR
FROM eclipse-temurin:17-jdk-alpine

# 2. Set the working directory
WORKDIR /app

# 3. Copy the JAR from your target folder
# Renaming it to 'app.jar' makes the next step easier
COPY target/CategoryService-0.0.1-SNAPSHOT.jar app.jar

# 4. Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]