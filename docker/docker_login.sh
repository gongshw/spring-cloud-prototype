#!/usr/bin/env bash

set +x

echo login to docker hub

if [ -n "${DOCKER_USERNAME}" -a -n "${DOCKER_PASSWORD}" ]; then
    docker login -u${DOCKER_USERNAME} -p${DOCKER_PASSWORD}
    echo login to docker hub done!
else
    echo no credential fount, abandon
fi
