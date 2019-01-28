@echo off 

REM @author Nils Christian Ehmke

setlocal enabledelayedexpansion

SET JAVAARGS=-Dkieker.monitoring.writer=kieker.monitoring.writer.tcp.SingleSocketTcpWriter -Dkieker.monitoring.writer.tcp.SingleSocketTcpWriter.flush=true -Dkieker.monitoring.timer=kieker.monitoring.timer.SystemMilliTimer -Dkieker.monitoring.timer.SystemMilliTimer.unit=2 -Dkieker.common.logging.Log=JDK -Djava.util.logging.config.file=./logging.properties -Xms56m -Xmx1024m
SET MAINCLASSNAME=kieker.tools.resourceMonitor.ResourceMonitor

REM Get the directory of this file and change the working directory to it.
cd %~dp0

REM Set every variable we will need for the execution.
SET BINDIR=%cd%

REM Now start the tool, but don't forget to deliver the parameters.
java %JAVAARGS% -cp "%BINDIR%\..\lib\*";"%BINDIR%\..\lib\sigar\*";"%BINDIR%\..\build\libs\*";"%BINDIR%" %MAINCLASSNAME% %*

REM Don't close the window immediately.
@echo on
PAUSE
