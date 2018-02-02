#!/bin/sh
./kiekerSystemMonitoring/kieker-1.14-SNAPSHOT/bin/resourceMonitor.sh &
java -javaagent:kieker-1.14-SNAPSHOT-aspectj.jar -cp . -jar sampleApplication.jar

