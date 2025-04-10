# build c maven
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# runtime
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/petshare-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8181

CMD ["java", "-jar", "app.jar"]
