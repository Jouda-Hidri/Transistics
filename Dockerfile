FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/app.jar /app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","/app.jar"]