FROM openjdk:11-jdk-slim

COPY target/service-2-1.0-SNAPSHOT.jar service-2.jar

ENTRYPOINT [ "sh", "-c", "java -jar service-2.jar" ]