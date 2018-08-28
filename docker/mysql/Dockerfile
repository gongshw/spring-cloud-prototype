FROM mysql:5.7

ENV MYSQL_DATABASE=db \
    MYSQL_ROOT_PASSWORD=root

ADD schema.sql /docker-entrypoint-initdb.d

EXPOSE 3306
