FROM openjdk:8-jdk-alpine
VOLUME /tmp
ENV ENV=dev
ENV CONFIG_SERVER="http://localhost:8801/"

ENV SERVER_PORT=8080
EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=${SERVER_PORT}", "-jar", "/app.jar"]

ARG JAR_FILE
COPY ${JAR_FILE} app.jar
