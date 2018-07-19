#!/usr/bin/env bash

set -e

echo starting build $1

module_path=".${1//://}"
jar_file="${module_path}/build/libs/*"
out_bin="output/bin"

bash gradlew $1:bootJar --info || exit 1


mkdir -p ${out_bin}

echo copying ${jar_file} to ${out_bin}
cp ${jar_file} ${out_bin}

bash gradlew $1:docker --info || echo "unable to create docker image. it's ok."

echo $1 build done!
