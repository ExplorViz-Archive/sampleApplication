package net.explorviz.sampleApplication.database;

import java.util.logging.Logger;

import net.explorviz.sampleApplication.database.helper.SQLConnectionHandler;
import net.explorviz.sampleApplication.database.helper.SQLStatementHandler;
import net.explorviz.sampleApplication.util.RandomNumberGenerator;

/**
 * JDBC Example Application Class for testing the new DatabaseAspect
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class JDBCExample {

	static Logger LOG = Logger.getLogger("global");
	public static final String DB_BASE_URL = "jdbc:sqlite:";
	private static final String databaseName = "test.db";

	/**
	 * Runs a set of sample queries
	 * 
	 * @param args
	 */
	public static void start() {
		runQueries();
	}

	/**
	 * Executes SQL queries for testing purposes
	 */
	private static void runQueries() {
		LOG.info("Creating database...");
		SQLConnectionHandler.createDatabase(databaseName);

		LOG.info("Start generating SQL queries...");

		// creates the table
		SQLStatementHandler.executeStatementHandler(databaseName, SQLStatementHandler.Querytype.statementExecute, "CREATE TABLE IF NOT EXISTS `order` "
				+ "(oid integer PRIMARY KEY, name text NOT NULL, email text NOT NULL, odate text NOT NULL, itemid integer NOT NULL);",
				null);

		// create queries based on the number of maximum iterations
		int maxIterations = 25;
		for (int i = 0; i < maxIterations; i++) {
			SQLStatementHandler.executeStatementHandler(databaseName, SQLStatementHandler.Querytype.statementExecute,
					"INSERT INTO `order` (oid, name, email, odate, itemid) " + "VALUES('" + RandomNumberGenerator.getRandomNumber()
							+ "', 'Tom B. Erichsen', 'erichsen@uni-kiel.de', '2017-11-16', '1');",
					null);
			SQLStatementHandler.executeStatementHandler(databaseName, SQLStatementHandler.Querytype.statementExecuteQuery,
					"INSERT INTO `order` (oid, name, email, odate, itemid) " + "VALUES('" + RandomNumberGenerator.getRandomNumber()
							+ "', 'Carol K. Durham', 'durham@uni-kiel.de', '2017-10-08', '1');",
					null);
			SQLStatementHandler.executeStatementHandler(databaseName, SQLStatementHandler.Querytype.preparedStatementExecute,
					"SELECT * FROM `order` WHERE name = ?", "Carol K. Durham");

			SQLStatementHandler.executeStatementHandler(databaseName, SQLStatementHandler.Querytype.preparedStatementExecuteQuery,
					"SELECT * FROM `order` WHERE name = ?", "Tom B. Erichsen");
		}
		LOG.info("Finished generating SQL queries...");
	}

}
