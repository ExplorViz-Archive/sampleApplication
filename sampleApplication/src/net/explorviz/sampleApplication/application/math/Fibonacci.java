package net.explorviz.sampleApplication.application.math;

/**
 * Calculates the fiboacci number for a given number
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class Fibonacci {

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
		return calculate(number - 1) + calculate(number - 2);
	}

}
