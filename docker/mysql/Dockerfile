FROM mysql:5.7.23

ENV MYSQL_DATABASE=db \
    MYSQL_ROOT_PASSWORD=root \
    TZ='Asia/Shanghai'

ADD schema.sql /docker-entrypoint-initdb.d

EXPOSE 3306
