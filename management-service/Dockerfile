FROM openjdk:11 AS build

COPY pom.xml mvnw ./
RUN chmod +x mvnw
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -Dmaven.test.skip

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR managementservice

COPY --from=build target/*.jar management-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","management-0.0.1-SNAPSHOT.jar"]