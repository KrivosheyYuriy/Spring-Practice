FROM openjdk:latest
#COPY /target/<name>-<version>.jar

ENTRYPOINT ["java", "-jar", "<name>-<version>.jar"]