#!/bin/bash

source ~/bin/setup-java-1.8.sh

npm install && ./node_modules/.bin/ng build --prod && mvn clean install
