FROM maven:3.9.7-eclipse-temurin-22-alpine as backend
WORKDIR /usr/src/spring/
COPY src ./src/
COPY pom.xml ./
RUN mvn install -DskipTests
RUN mvn package -DskipTest

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./target/blankgameback.jar"]

