#!/bin/sh

wget https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar
wget https://github.com/open-telemetry/opentelemetry-collector-releases/releases/download/v0.62.1/otelcol-contrib_0.62.1_darwin_amd64.tar.gz
wget https://raw.githubusercontent.com/open-telemetry/opentelemetry-collector/main/examples/local/otel-config.yaml
