#!/bin/bash

javaVersion=$(javac -version 2>&1 | awk '{ print $2 }')

if [[ ! ( "$javaVersion" =~ 1.8.* ) ]] ;then
    if [ -f ~/bin/setup-java-1.8.sh ] ;then
        source ~/bin/setup-java-1.8.sh
    else
        echo "You're not using Java 1.8; please fix."
        exit 2
    fi
fi

npm install && ./node_modules/.bin/ng build --prod && mvn clean install
