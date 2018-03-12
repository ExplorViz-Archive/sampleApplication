#!/bin/sh
./kiekerSystemMonitoring/kieker-1.14-SNAPSHOT/bin/resourceMonitor.sh &
java -javaagent:kieker-1.14-SNAPSHOT-aspectj.jar -Dkieker.monitoring.skipDefaultAOPConfiguration=true -cp . -jar sampleApplication.jar

