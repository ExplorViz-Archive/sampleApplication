:: ==============================================================
:: Script for starting the monitoring component for ExplorViz
::
:: Christian Zirkelbach (czi@informatik.uni-kiel.de)
:: ==============================================================
@ECHO OFF

REM Get the directory of this file and change the working directory to it.
cd %~dp0

REM Set every variable we will need for the execution.
SET BINDIR=%cd%

START "kiekerSystemMonitoring" ".\kiekerSystemMonitoring\kieker-1.14-SNAPSHOT\bin\resourceMonitor.bat"
java -javaagent:kieker-1.14-SNAPSHOT-aspectj.jar -Dkieker.monitoring.configuration=.\META-INF\kieker.monitoring.properties -Dorg.aspectj.weaver.loadtime.configuration=.\META-INF\aop.xml -cp . -jar sampleApplication.jar