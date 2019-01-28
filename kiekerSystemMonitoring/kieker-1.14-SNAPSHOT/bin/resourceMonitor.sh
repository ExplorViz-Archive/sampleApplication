#!/bin/bash

# Starts a stand-alone tool to monitor the CPU utilization and memory/swap usage
#
# @author Andre van Hoorn

BINDIR=$(cd "$(dirname "$0")"; pwd)/

JAVAARGS="-Dkieker.monitoring.writer=kieker.monitoring.writer.tcp.SingleSocketTcpWriter -Dkieker.monitoring.writer.tcp.SingleSocketTcpWriter.flush=true -Dkieker.monitoring.timer=kieker.monitoring.timer.SystemMilliTimer -Dkieker.monitoring.timer.SystemMilliTimer.unit=2 -Dkieker.common.logging.Log=JDK -Djava.util.logging.config.file=${BINDIR}/logging.properties -Xms56m -Xmx1024m"
MAINCLASSNAME=kieker.tools.resourceMonitor.ResourceMonitor

java ${JAVAARGS} -cp "${BINDIR}/../lib/*":"${BINDIR}/../lib/sigar/*":"${BINDIR}/../build/libs/*":"${BINDIR}" ${MAINCLASSNAME} $*
