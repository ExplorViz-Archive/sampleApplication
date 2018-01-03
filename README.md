# kiekerSampleApplication
A straightforward sample application generating monitoring data for [ExplorViz](https://www.explorviz.net).
The monitoring data is gathered by the [Kieker](http://kieker-monitoring.net/) monitoring component.

Generates monitoring data employing the following Kieker aspects
- *aspectj.operationExecution.FullInstrumentationNoGetterAndSetter (Java application calls)*
- *aspectj.database.Fullinstrumentation (JDBC related database calls)*

Usage:
`./runMonitoredApplication.sh` (Unix) or `./runMonitoredApplication.bat` (Windows)

For continuous monitoring data, use instead the following scripts:
`./runMonitoredApplicationLoop.sh` (Unix) or `./runMonitoredApplicationLoop.bat` (Windows)

For more information, visit the [https://www.explorviz.net] ExplorViz website.
