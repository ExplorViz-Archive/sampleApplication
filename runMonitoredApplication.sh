#!/bin/sh
#  ==============================================================
# Script for starting the monitoring component for ExplorViz
#
# Christian Zirkelbach (czi@informatik.uni-kiel.de)
# ==============================================================
./kiekerSystemMonitoring/kieker-1.14-SNAPSHOT/bin/resourceMonitor.sh &
java -javaagent:kieker-1.14-SNAPSHOT-aspectj.jar -Dkieker.monitoring.configuration=./META-INF/kieker.monitoring.properties -Dorg.aspectj.weaver.loadtime.configuration=./META-INF/aop.xml -cp . -jar sampleApplication.jar

