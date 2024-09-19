FROM openjdk:21
EXPOSE 8765
ADD target/Gateway_service-0.0.1-SNAPSHOT.jar Gateway.jar
ENTRYPOINT ["java", "-jar", "/Gateway.jar"]
