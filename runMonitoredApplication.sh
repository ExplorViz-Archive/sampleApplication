#!/bin/sh
# ==============================================================
# Script for starting the monitoring component for ExplorViz
#
# Christian Zirkelbach (czi@informatik.uni-kiel.de)
# ==============================================================

# When this script exits (e.g. due to Ctrl-C) kill all our children as well
trap "trap - TERM && kill 0" INT TERM EXIT

./kiekerSystemMonitoring/kieker-1.14-SNAPSHOT/bin/resourceMonitor.sh &
java -javaagent:kieker-1.14-SNAPSHOT-aspectj.jar -Dkieker.monitoring.skipDefaultAOPConfiguration=true -Dkieker.monitoring.configuration=./META-INF/kieker.monitoring.sampleApplication.properties -Dorg.aspectj.weaver.loadtime.configuration=./META-INF/aop.sampleApplication.xml -cp . -jar sampleApplication.jar &
java -javaagent:kieker-1.14-SNAPSHOT-aspectj.jar -Dkieker.monitoring.skipDefaultAOPConfiguration=true -Dkieker.monitoring.configuration=./META-INF/kieker.monitoring.remoteSampleApplicationServer.properties -Dorg.aspectj.weaver.loadtime.configuration=./META-INF/aop.remoteSampleApplicationServer.xml -cp . -jar remoteSampleApplicationServer.jar &

# Allow server some time to start before starting clients
sleep 3

while true; do
    java -javaagent:kieker-1.14-SNAPSHOT-aspectj.jar -Dkieker.monitoring.skipDefaultAOPConfiguration=true -Dkieker.monitoring.configuration=./META-INF/kieker.monitoring.remoteSampleApplicationClient.properties -Dorg.aspectj.weaver.loadtime.configuration=./META-INF/aop.remoteSampleApplicationClient.xml -cp . -jar remoteSampleApplicationClient.jar &
    sleep 5
done
