package net.explorviz.sampleApplication.application.math;

import java.util.logging.Logger;

import net.explorviz.sampleApplication.util.RandomNumberGenerator;

/**
 * Calculates the fiboacci number for a given number and uses artifical
 * busy-waiting for more random repsonse times
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class Fibonacci {

	static Logger LOG = Logger.getLogger("global");

	/**
	 * Calculates the fibonacci number of a passed parameter
	 * 
	 * @param number
	 * @return
	 */
	public static int calculate(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}

		// try to sleep for a random number of milliseconds
		try {
			Thread.sleep(RandomNumberGenerator.getRandomNumber(1, 5000));
		} catch (InterruptedException e) {
			LOG.severe(e.getMessage());
		}

		return calculate(number - 1) + calculate(number - 2);
	}

}
