FROM openjdk:11 AS build

COPY pom.xml mvnw ./
RUN chmod +x mvnw
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -Dmaven.test.skip

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR configserver

COPY --from=build target/*.jar configserver-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","configserver-0.0.1-SNAPSHOT.jar"]