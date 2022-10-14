#!/bin/sh

watch "curl -s localhost:8080/actuator/prometheus | egrep '^http_'"

