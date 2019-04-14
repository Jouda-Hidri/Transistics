FROM maven:3.3.3 AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","/tmp/target/app.jar"] 