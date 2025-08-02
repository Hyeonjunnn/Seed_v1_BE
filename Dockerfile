LABEL authors="songhyeonjun"

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/Seed_v1_BE-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
