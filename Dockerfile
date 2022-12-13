#
# Build stage
#
FROM maven:3.8.6-amazoncorretto-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM amazoncorretto:17-alpine-jdk
COPY --from=build /home/app/target/*.jar /usr/local/lib/marketdata.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/marketdata.jar"]

