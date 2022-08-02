FROM openjdk:11 AS build

COPY pom.xml mvnw ./
RUN chmod +x mvnw
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -Dmaven.test.skip

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR advertisementservice

COPY --from=build target/*.jar advertisement-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","advertisement-0.0.1-SNAPSHOT.jar"]