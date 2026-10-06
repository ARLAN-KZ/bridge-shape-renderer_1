#!/usr/bin/env bash
# Compile and run the demo + self-checks (JDK 17+).
set -e
cd "$(dirname "$0")/.."
rm -rf out && mkdir out
javac -d out $(find src -name '*.java')
java -cp out bridge.Main
java -ea -cp out bridge.BridgeSmokeTest
