# kiekerSampleApplication
A straightforward sample application generating monitoring data for [ExplorViz](https://www.explorviz.net).
The monitoring data is gathered by the [Kieker](http://kieker-monitoring.net/) monitoring component.

Generates monitoring data employing the following Kieker aspects
- *aspectj.operationExecution.FullInstrumentationNoGetterAndSetter (Java application calls)*
- *aspectj.database.Fullinstrumentation (JDBC related database calls)*

Usage:
`./runMonitoredApplication.sh` (Unix) or `./runMonitoredApplication.bat` (Windows)

The monitoring script is running until termination.

For more information, visit the [ExplorViz](https://www.explorviz.net) website.
