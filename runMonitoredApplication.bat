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
START "sampleApplication" java -javaagent:kieker-1.14-SNAPSHOT-aspectj.jar -Dkieker.monitoring.configuration=.\META-INF\kieker.monitoring.sampleApplication.properties -Dorg.aspectj.weaver.loadtime.configuration=.\META-INF\aop.sampleApplication.xml -cp . -jar sampleApplication.jar
START "sampleServer" java -javaagent:kieker-1.14-SNAPSHOT-aspectj.jar -Dkieker.monitoring.configuration=.\META-INF\kieker.monitoring.remoteSampleApplicationServer.properties -Dorg.aspectj.weaver.loadtime.configuration=.\META-INF\aop.remoteSampleApplicationServer.xml -cp . -jar remoteSampleApplicationServer.jar

timeout /t 3

:loopClient
 START "sampleClient" java -javaagent:kieker-1.14-SNAPSHOT-aspectj.jar -Dkieker.monitoring.configuration=.\META-INF\kieker.monitoring.remoteSampleApplicationClient.properties -Dorg.aspectj.weaver.loadtime.configuration=.\META-INF\aop.remoteSampleApplicationClient.xml -cp . -jar remoteSampleApplicationClient.jar
 timeout /t 5
 goto loopClient
