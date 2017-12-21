package net.explorviz.sampleApplication;

import net.explorviz.sampleApplication.application.JavaExample;
import net.explorviz.sampleApplication.database.JDBCExample;

/**
 * Sample Application for testing the monitoring component (Kieker) for
 * ExplorViz
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class Main {
	public static void main(String[] args) {
		JavaExample.start();
		JDBCExample.start();
	}

}