package net.explorviz.sampleApplication.application;

import java.util.logging.Logger;

/**
 * JDBC example application class for testing the usual instrumentation aspects
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class JavaExample {

	private static final Logger LOG = Logger.getLogger(JavaExample.class.getName());

	public static void start() {
		LOG.info("Calculating fibonacci numbers...");
		LOG.info("Fibonacci of 5: " + fibonacci(5));
		LOG.info("Fibonacci of 10: " + fibonacci(10));
		LOG.info("Fibonacci of 25: " + fibonacci(25));
		LOG.info("Finished calculating fibonacci numbers.");
	}

	/**
	 * Calculates the fibonacci number of a passed parameter
	 * 
	 * @param number
	 * @return
	 */
	public static int fibonacci(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}
		return fibonacci(number - 1) + fibonacci(number - 2);
	}

}