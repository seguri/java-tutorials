#!/bin/sh

curl -s localhost:8080/actuator/mappings | jq -e '.contexts.application.mappings.dispatcherServlets.dispatcherServlet[].predicate'
