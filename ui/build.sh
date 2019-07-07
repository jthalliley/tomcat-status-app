#!/bin/bash

javac -version

npm install && ./node_modules/.bin/ng build --prod && mvn clean install
