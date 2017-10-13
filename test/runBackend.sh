cd $(dirname $0)
cd ../countries

mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=noraui -Dsonar.login=$SONAR_TOKEN -Punit-tests

java -jar target/countries-0.0.1-SNAPSHOT.jar &
PID=$!
sleep 30

curl --cookie-jar cookie -L http://localhost:8084/health
TOKEN=$( cat cookie | grep 'JSESSIONID' | cut -f7 )
echo "*********************** $TOKEN"

curl --cookie cookie -u admin:secret -d "_csrf=$TOKEN" -L http://localhost:8084/health > target/actual_health.json
echo "Let's look at the actual health: `cat target/actual_health.json`"
echo "And compare it to: `cat ../test/health.json`"
if diff -w ../test/health.json target/actual_health.json
    then
        echo SUCCESS
    else
        echo FAIL
        exit 255
fi


curl -s http://localhost:8084/countries/api/v1/flags/countries > target/actual_countries.json
curl -s http://localhost:8084/countries/api/v1/flags/countries?lang=en > target/actual_countries_EN.json
curl -s http://localhost:8084/countries/api/v1/flags/countries?lang=fr > target/actual_countries_FR.json
kill -9 $PID

echo "Let's look at the actual results: `cat target/actual_countries.json`"
echo "And compare it to: `cat ../test/expected_countries_EN.json`"
if diff -w ../test/expected_countries_EN.json target/actual_countries.json
    then
        echo SUCCESS
    else
        echo FAIL
        exit 255
fi

echo "Let's look at the actual results: `cat target/actual_countries_EN.json`"
echo "And compare it to: `cat ../test/expected_countries_EN.json`"
if diff -w ../test/expected_countries_EN.json target/actual_countries_EN.json
    then
        echo SUCCESS
    else
        echo FAIL
        exit 255
fi

echo "Let's look at the actual results: `cat target/actual_countries_FR.json`"
echo "And compare it to: `cat ../test/expected_countries_FR.json`"
if diff -w ../test/expected_countries_FR.json target/actual_countries_FR.json
    then
        echo SUCCESS
    else
        echo FAIL
        exit 255
fi

exit 0
