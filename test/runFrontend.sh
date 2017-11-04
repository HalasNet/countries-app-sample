#!/usr/bin/env bash
cd $(dirname $0)
cd ../webclient

echo "***** START ANGULAR INSTALLATION"
npm install -g @angular/cli

echo "***** NPM INSTALL"
npm install

echo "***** START ANGULAR APPLICATION"
npm start &
sleep 120

echo "***** CURL to home url"
curl -s http://localhost:4200

ls
echo "***** END"
exit 0
