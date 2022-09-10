FROM openjdk:11
EXPOSE 8080
COPY build/libs/nttdata-0.0.1-SNAPSHOT.jar nttdata-challenge.jar
ENTRYPOINT ["java", "-jar", "/nttdata-challenge.jar"]