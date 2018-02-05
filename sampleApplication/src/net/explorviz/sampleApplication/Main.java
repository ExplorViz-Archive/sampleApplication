package net.explorviz.sampleApplication;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import net.explorviz.sampleApplication.application.JavaExample;
import net.explorviz.sampleApplication.database.JDBCExample;
import net.explorviz.sampleApplication.util.RandomNumberGenerator;

/**
 * Sample Application for testing the monitoring component (Kieker) for
 * ExplorViz
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class Main {

	// Enable or disable logging
	public final static boolean ENABLE_LOGGING = false;

	static class ApplicationTask extends TimerTask {
		@Override
		public void run() {
			JavaExample.start();
		}
	}

	static class DatabaseTask extends TimerTask {
		@Override
		public void run() {
			JDBCExample.start();
		}
	}

	public static void main(String[] args) {

		if (!ENABLE_LOGGING) {
			LogManager.getLogManager().reset();
			Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
			globalLogger.setLevel(java.util.logging.Level.OFF);
		}
		
		Timer timer = new Timer();
		timer.schedule(new ApplicationTask(), RandomNumberGenerator.getRandomNumber(1000, 4000));
		timer.schedule(new DatabaseTask(), 1000, RandomNumberGenerator.getRandomNumber(500, 5000));
		timer.schedule(new ApplicationTask(), 2000, RandomNumberGenerator.getRandomNumber(6000, 8000));
	}
}