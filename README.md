# Spring Cloud Prototype

Prototype codes to build a multi-module spring cloud project.

## Preparation

- JDK 8 Installed
- Docker Daemon Running

## Build Docker Images

```bash
sudo -E ./for_each_app ./docker.modlue.sh build
```
The build process requires an access to fetch following internet resources:
- Jcenter Repository
- Docker Hub Repository
- Alpine Linux Repository

## Run Applications in Docker
```bash
sudo -E docker-compose -p focussource-demo -f docker/docker-compose.yml up -d
```
 
After all applications started up, visit the Spring Boot Admin Doashboard
([http://localhost:8803/](http://localhost:8803/)) to check registered services states.

### Services List

| Docker Image Name         | Port Exposed | Port In Container| Description                       |
| ------------------------- | ------------ | ---------------- | --------------------------------- |
| focussource/config-server | 8801         | 80               | Spring Cloud Config Server        |
| focussource/eureka-server | 8802         | 80               | Spring Cloud Eureka Server        |
| focussource/admin-server  | 8803         | 80               | Spring Cloud Admin Dashboard      |
| focussource/auth-server   | 8804         | 80               | Spring Cloud Authorization Server |
| focussource/demo1         | Random       | 80               | Demo Application Service #1       |
| focussource/demo2         | Random       | 80               | Demo Application Service #2       |
| focussource/zuul-proxy    | 8080         | 80               | Spring Cloud Zuul Proxy           |
| openzipkin/zipkin         | 9411         | 9411             | Zipkin Tracing System             |

### Get User Token (Oauth2 + JWT)

```bash
access_token=`curl -s -u web:web-password http://127.0.0.1:8080/oauth/token \
    -d "password=user&username=user&grant_type=password" \
    | python3 -c "import sys, json; print(json.load(sys.stdin)['access_token'])"`
echo $access_token

# get something like:
# access_token=eyJhbGciOiJI...
```

### Send Request With Token

```bash
# hello word
curl -H "Authorization: bearer ${access_token}"  http://127.0.0.1:8080/demo2/find

# get user name
curl -H "Authorization: bearer ${access_token}"  http://127.0.0.1:8080/demo1/security/name

# 403 forbidden
curl -H "Authorization: bearer ${access_token}"  http://127.0.0.1:8080/demo1/security/checkAdmin
```

### Tracing Dashboard

Visit Zipkin Dashboard ([http://127.0.0.1:9411/](http://127.0.0.1:9411/)) for  tracing data.

## Push Docker Images To Docker Hub
```bash
sudo -E ./for_each_app ./docker.modlue.sh push
```
