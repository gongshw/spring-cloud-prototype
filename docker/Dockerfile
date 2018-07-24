FROM openjdk:8-jdk-alpine

ARG JAR_FILE
ARG APP_NAME
ENV APP_NAME ${APP_NAME}

RUN apk add --no-cache bash curl python3

VOLUME /tmp
ENV ENV=dev
ENV CONFIG_SERVER="http://localhost:8801"

ENV SERVER_PORT=80
EXPOSE ${SERVER_PORT}

ENTRYPOINT ["python3", "/application-entrypoint.py"]

COPY application-entrypoint.py application-entrypoint.py
COPY wait-for-it.sh wait-for-it.sh
COPY ${JAR_FILE} app.jar
