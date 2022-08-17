#!/bin/bash

curl -s "http://localhost:8080/greeting?name=$(shuf -n 1 names.txt)"
