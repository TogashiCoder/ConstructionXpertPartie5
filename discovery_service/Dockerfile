FROM openjdk:21
EXPOSE 8080
ADD target/discovery_service-0.0.1-SNAPSHOT.jar discovery.jar
ENTRYPOINT ["java", "-jar", "/discovery.jar"]
