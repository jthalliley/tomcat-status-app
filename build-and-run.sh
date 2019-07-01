#!/bin/bash

cd ui && ./build.sh

cd ../app && ./build.sh

cd .. && ./run.sh
