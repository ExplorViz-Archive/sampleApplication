package net.explorviz.sampleApplication.database.helper;

import java.util.Random;

/**
 * Generates random numbers
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class RandomNumberGenerator {

	/**
	 * Returns a random number between low and high
	 * 
	 * @return
	 */
	public static int getRandomNumber() {
		Random r = new Random();
		int Low = 10;
		int High = 100000;
		return r.nextInt(High - Low) + Low;
	}

}
