#!/usr/bin/env bash

find . -name "*.java" > src.txt
javac -d . @src.txt
java Simulator /scenario.txt
rm *.class

