FROM maven:3.3.3
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","/tmp/target/app.jar"] 