# Use a JDK base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper & project files
COPY . .

RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/Job-Dhundo-0.0.1-SNAPSHOT.jar"]
