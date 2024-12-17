FROM openjdk:latest
COPY ./target/app.jar app.jar

CMD ["java", "-jar", "app.jar"]