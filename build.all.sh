#!/usr/bin/env bash

set -e

build_module(){
    bash ./build.module.sh $1 || exit 1
}

bash gradlew clean check

echo "building base applications:"
build_module :base:admin-server
build_module :base:eureka-server
build_module :base:zuul-proxy
build_module :base:config-server

echo "building service applications:"
build_module :service:demo2
build_module :service:demo1:demo1-app
