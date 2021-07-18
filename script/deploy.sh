#!/usr/bin/env bash

mvn clean package

pgrep java | xargs kill -9
nohup java -jar XmlRedactor-1.0-SNAPSHOT>log.txt
java -jar ./target/XmlRedactor-1.0-SNAPSHOT.jar



echo 'Bye...'



