#!/bin/sh

export OTEL_JAVAAGENT_DEBUG=true
export OTEL_TRACES_EXPORTER=logging
export OTEL_METRICS_EXPORTER=logging

java -javaagent:assets/opentelemetry-javaagent.jar -jar target/spring-opentelemetry-*.jar
