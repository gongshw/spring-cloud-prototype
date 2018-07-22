#!/usr/bin/env bash

set -x

echo starting build $1

module_path=".${1//://}"
jar_file="${module_path}/build/libs/*"
out="output"
out_bin="${out}/bin"

bash gradlew $1:bootJar --stacktrace || exit 1


mkdir -p ${out_bin}

echo copying ${jar_file} to ${out_bin}
cp ${jar_file} ${out_bin}
cp docker/Dockerfile  ${out}

echo $1 build done!
