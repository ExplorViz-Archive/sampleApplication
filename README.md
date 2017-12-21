# kiekerSampleApplication
A straightforward sample application for testing the [Kieker](http://kieker-monitoring.net/) monitoring component 
for generating monitoring data for [ExplorViz](https://www.explorviz.net).

Generates monitoring data employing the following Kieker aspects
- *aspectj.operationExecution.FullInstrumentationNoGetterAndSetter (Java application calls)*
- *aspectj.database.Fullinstrumentation (JDBC related database calls)*

Usage:
`./runMonitoredApplication.sh`

For more information, visit the ExplorViz website (https://www.explorviz.net).
