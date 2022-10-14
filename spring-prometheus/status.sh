#!/bin/bash

curl -s "http://localhost:8080/status/$(shuf -n 1 statuses.txt)"
