FROM openjdk:11-jdk-slim

COPY target/service-1-1.0-SNAPSHOT.jar service-1.jar

ENTRYPOINT [ "sh", "-c", "java -jar service-1.jar" ]