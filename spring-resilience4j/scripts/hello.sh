#!/bin/sh

n=${1:-10}
ab -n $n -c 10 -s 10 http://localhost:8080/ratelimiter/hello
