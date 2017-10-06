#!/bin/sh
cd $(dirname $0)
pwd
cd ..
pwd
ls -l
echo "**********************************************************"
echo "*                                                        *"
echo "*    generate a robot from noraui-archetype              *"
echo "*                                                        *"
echo "**********************************************************"
mvn archetype:generate -DarchetypeGroupId=com.github.noraui -DarchetypeArtifactId=noraui-archetype -DarchetypeVersion=2.6.2 -DgroupId=com.your.company -DartifactId=robot -Dversion=0.0.1-SNAPSHOT -DinteractiveMode=false

echo "**********************************************************"
echo "*                                                        *"
echo "*    the tester develops its scenarios at this stage.    *"
echo "*                                                        *"
echo "**********************************************************"

pwd
ls -l
cd robot
echo "**********************************************************"
echo "*                                                        *"
echo "*    create the robot as a dependency                    *"
echo "*                                                        *"
echo "**********************************************************"
mvn clean install

echo "**********************************************************"
echo "*                                                        *"
echo "*    run test via robot for coverage                     *"
echo "*                                                        *"
echo "**********************************************************"
cd ../countries
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=noraui -Dsonar.login=$SONAR_TOKEN -Punit-tests -Dmaven.test.failure.ignore=true

curl -s "https://api.travis-ci.org/jobs/${TRAVIS_JOB_ID}/log.txt?deansi=true" > countries.log

exit 0