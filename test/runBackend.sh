cd $(dirname $0)
cd ../countries

mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=noraui -Dsonar.login=$SONAR_TOKEN -Punit-tests

java -jar target/countries-0.0.1-SNAPSHOT.jar &
PID=$!
sleep 30

kill -9 $PID

exit 0
