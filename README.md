# ExplorViz kiekerSampleApplication
A straightforward sample application generating monitoring data for [ExplorViz](https://www.explorviz.net).

The monitoring data is gathered by the [Kieker](http://kieker-monitoring.net/) monitoring component.

Generates monitoring data employing the following Kieker aspects
- *aspectj.operationExecution.FullInstrumentationNoGetterAndSetter (Java application calls)*
- *aspectj.database.Fullinstrumentation (JDBC related database calls)*

```
Attention

This set up is provided to instrument directly executed Java applications.
The set up for application servers is under revision right now and will be released later.
```
Usage:
`./runMonitoredApplication.sh` (Unix) or `./runMonitoredApplication.bat` (Windows)

The monitoring script is running until termination.

## Configuration Options
### Monitoring scope
Path: `META-INF/aop.xml`
- Choose the packages, which should be monitored

	`<include within="net.explorviz.sampleApplication..*"/>`
- Exclude packages, which should **not** be monitored

	`<exclude within="org.apache.commons.logging..*" />`
	
### Application name
Path: `META-INF/kieker.monitoring.properties`
- Change the name of the application

	`kieker.monitoring.applicationName=sampleApplication`
- Change the ip address of the ExplorViz server
	
	`kieker.monitoring.writer.tcp.SingleSocketTcpWriter.hostname=localhost`
	
	*(only necessary, if the server and monitoring are running on different hosts)*

For more information, visit the [ExplorViz](https://www.explorviz.net) website.
