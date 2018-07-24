#!/usr/bin/env bash

set -x

echo starting push $1 to docker hub

bash +x docker/docker_login.sh

if [ -n "${CI_COMMIT_REF_NAME}" ]; then
    GIT_BRANCH="${CI_COMMIT_REF_NAME}"
else
    GIT_BRANCH=`git branch | grep \* | cut -d ' ' -f2`
fi

if [ -n "${CI_COMMIT_SHA}" ]; then
    GIT_VERSION=${CI_COMMIT_SHA:0:8}
else
    GIT_VERSION=`git rev-parse --short HEAD`
fi

echo current git version: ${GIT_VERSION}, branch: ${GIT_BRANCH}

if [ -z "$(git status --porcelain)" ]; then
    VERSION="${GIT_BRANCH}.`git rev-parse --short HEAD`"
else
    echo current git repo not clean, will build a snapshot image
fi

case $1 in
    build)
        bash gradlew -PVERSION=${VERSION} -PBRANCH=${GIT_BRANCH} $2:docker --stacktrace || exit 1
        echo $1 build image $2 done!
        ;;
    push)
        bash gradlew -PVERSION=${VERSION} -PBRANCH=${GIT_BRANCH} $2:dockerPush --stacktrace || exit 1
        echo $1 pushed image $2 to docker hub
        ;;
    *)
        cat << EOF
Usage:
    $0 build {app}
    $0 push {app}
EOF
        ;;
esac
