package net.explorviz.sampleApplication.util;

import java.util.Random;

/**
 * Generates random numbers
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class RandomNumberGenerator {

	/**
	 * Returns a random number between 10 and 100000
	 * 
	 * @return
	 */
	public static int getRandomNumber() {
		Random r = new Random();
		int low = 10;
		int high = 100000;
		return r.nextInt(high - low) + low;
	}

	/**
	 * Returns a random number between low and high
	 * 
	 * @param low
	 * @param high
	 * @return
	 */
	public static int getRandomNumber(int low, int high) {

		if (low > 0 && high > 0) {
			Random r = new Random();
			return r.nextInt(high - low) + low;
		} else
			return 0;
	}

}
