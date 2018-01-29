package net.explorviz.sampleApplication.application;

import java.util.logging.Logger;

import net.explorviz.sampleApplication.application.math.Fibonacci;

/**
 * JDBC example application class for testing the usual instrumentation aspects
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class JavaExample {

	static Logger LOG = Logger.getLogger("global");

	public static void start() {
		LOG.info("Calculating fibonacci numbers...");
		LOG.info("Fibonacci of 3: " + Fibonacci.calculate(3));
		LOG.info("Fibonacci of 3: " + Fibonacci.calculate(3));
		LOG.info("Fibonacci of 3: " + Fibonacci.calculate(3));
		LOG.info("Fibonacci of 5: " + Fibonacci.calculate(5));
		LOG.info("Fibonacci of 5: " + Fibonacci.calculate(5));
		LOG.info("Fibonacci of 5: " + Fibonacci.calculate(5));
		LOG.info("Fibonacci of 10: " + Fibonacci.calculate(10));
		LOG.info("Finished calculating fibonacci numbers.");
	}

}