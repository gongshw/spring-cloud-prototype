#!/usr/bin/env bash

set -e

build_module(){
    bash ./build.module.sh $1 || exit 1
}

bash gradlew clean check --info

echo "building base applications:"
build_module :base:config-server
build_module :base:eureka-server
build_module :base:admin-server
build_module :base:auth-server
build_module :base:zuul-proxy

echo "building service applications:"
build_module :service:demo1:demo1-app
build_module :service:demo2
