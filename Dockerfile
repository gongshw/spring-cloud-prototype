FROM openjdk:8-jdk-alpine
VOLUME /tmp
ENV ENV=dev
ENV CONFIG_SERVER="http://localhost:8801/"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

ARG JAR_FILE
COPY ${JAR_FILE} app.jar
