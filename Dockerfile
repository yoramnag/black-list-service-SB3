#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER yoramnag@gmail.com

# Add the application's jar to the image
COPY target/black-list-service-0.0.1-SNAPSHOT.jar baseProject-0.0.1-SNAPSHOT.jarblack-list-service-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "black-list-service-0.0.1-SNAPSHOT.jar"]