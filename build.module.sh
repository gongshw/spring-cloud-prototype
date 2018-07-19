#!/usr/bin/env bash

echo starting build $1

jar_file=".${1//://}/build/libs/*"
out_bin="output/bin"

sh gradlew $1:bootJar || echo "fail to build module $1" && echo "exiting build..." && exit -1


mkdir -p ${out_bin}

jar_file=".${1//://}/build/libs/*"
echo copying ${jar_file} to ${out_bin}
cp ${jar_file} ${out_bin}


echo $1 build done!
