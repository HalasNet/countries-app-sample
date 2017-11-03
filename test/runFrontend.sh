#!/usr/bin/env bash
cd $(dirname $0)
cd ../webclient

echo "***** START ANGULAR APPLICATION"
ng serve
sleep 120

echo "***** CURL to home url"
curl -s http://localhost:4200

echo "***** END"
exit 0
